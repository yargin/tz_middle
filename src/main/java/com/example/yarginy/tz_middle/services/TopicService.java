package com.example.yarginy.tz_middle.services;

import com.example.yarginy.tz_middle.handlers.TopicHandler;
import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Topic;
import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

import static java.util.stream.Collectors.*;

@Service
public class TopicService {
    public static final Logger logger = LoggerFactory.getLogger(TopicService.class);
    private static final String TOPICS_CACHE = "topics";
    private final ConcurrentMap<Integer, Topic> topicsCache;
    private final TopicMapper topicMapper;
    private final TopicHandler topicHandler;
    private final ItemService itemService;

    public TopicService(TopicMapper topicMapper, TopicHandler topicHandler, ItemService itemService,
                        @Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.topicMapper = topicMapper;
        this.topicHandler = topicHandler;
        this.itemService = itemService;
        topicsCache = hazelcastInstance.getMap(TOPICS_CACHE);
    }

    public Collection<Topic> selectAll() {
        Collection<Topic> unsortedTopics = new ArrayList<>();
        topicsCache.forEach((k, v) -> unsortedTopics.add(v));
        Collection<Topic> selectedTopics;
        if (unsortedTopics.isEmpty()) {
            selectedTopics = topicMapper.selectAll(topicHandler);
            selectedTopics.forEach(topic -> topicsCache.put(topic.getId(), topic));
            unsortedTopics.addAll(selectedTopics);
        }
        return unsortedTopics.stream().sorted((t1, t2) -> {
            if (t1.getId() > t2.getId()) {
                return 1;
            } else if (t1.getId() < t2.getId()) {
                return -1;
            }
            return 0;
        }).collect(toList());
    }

    public Topic selectTopicById(Integer id) {
        topicMapper.selectTopicById(id, topicHandler);
        return topicHandler.getTopic();
    }

    @Transactional
    public Topic insert(Topic topic) {
        topicMapper.insert(topic);
        itemService.insertFromTopic(topic);
        topicsCache.put(topic.getId(), topic);
        return topic;
    }

    @Transactional
    public Topic update(Topic topic) {
        itemService.updateFromTopic(topic);
        topicMapper.update(topic);
        topicsCache.put(topic.getId(), topic);
        return topic;
    }

    @Transactional
    public Topic delete(Integer id) {
        topicMapper.delete(id);
        Topic deletedTopic = topicsCache.get(id);
        topicsCache.remove(id, deletedTopic);
        return deletedTopic;
    }

    public void deleteAll() {
        if (topicMapper.deleteAll()) {
            topicsCache.forEach((k, v) -> topicsCache.remove(k));
        }
    }
}
