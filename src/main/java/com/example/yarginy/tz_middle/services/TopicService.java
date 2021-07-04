package com.example.yarginy.tz_middle.services;

import com.example.yarginy.tz_middle.handlers.TopicHandler;
import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

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
        itemService.insert(topic.getItems());
        return topicInserted;
    }

    @Transactional
    public boolean update(Topic topic) {
        Collection<Item> storedItems = itemService.selectByTopic(topic);
        itemService.update(storedItems, topic.getItems());
        return topicMapper.update(topic);
    }

    public boolean delete(Topic topic) {
        return topicMapper.delete(topic);
    }
}
