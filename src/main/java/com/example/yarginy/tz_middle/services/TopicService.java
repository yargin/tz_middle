package com.example.yarginy.tz_middle.services;

import com.example.yarginy.tz_middle.handlers.TopicHandler;
import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Topic;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TopicService {
    private final TopicMapper topicMapper;
    private final TopicHandler topicHandler;
    private final ItemService itemService;

    public TopicService(TopicMapper topicMapper, TopicHandler topicHandler, ItemService itemService) {
        this.topicMapper = topicMapper;
        this.topicHandler = topicHandler;
        this.itemService = itemService;
    }

    @Cacheable("topics")
    public Collection<Topic> selectAll() {
        return topicMapper.selectAll(topicHandler);
    }

    public Topic selectTopicById(Integer id) {
        topicMapper.selectTopicById(id, topicHandler);
        return topicHandler.getTopic();
    }

    @Transactional
    public boolean insert(Topic topic) {
        boolean topicInserted = topicMapper.insert(topic);
        itemService.insertFromTopic(topic);
        return topicInserted;
    }

    @Transactional
    public boolean update(Topic topic) {
        itemService.updateFromTopic(topic);
        return topicMapper.update(topic);
    }

    public boolean delete(Integer id) {
        return topicMapper.delete(id);
    }

    public boolean deleteAll() {
        return topicMapper.deleteAll();
    }
}
