package com.example.yarginy.tz_middle.mappers;

import com.example.yarginy.tz_middle.models.Topic;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TopicHandledMapper {
    private final TopicMapper topicMapper;
    private final TopicHandler topicHandler;

    public TopicHandledMapper(TopicMapper topicMapper, TopicHandler topicHandler) {
        this.topicMapper = topicMapper;
        this.topicHandler = topicHandler;
    }

    public Collection<Topic> selectAll() {
        return topicMapper.selectAll();
    }

    public Topic selectTopicById(Integer id) {
        topicMapper.selectTopicById(id, topicHandler);
        return topicHandler.getTopic();
    }

    public boolean insert(Topic topic) {
        return topicMapper.insert(topic);
    }

    public boolean update(Topic topic) {
        return topicMapper.update(topic);
    }

    public boolean delete(Topic topic) {
        return topicMapper.delete(topic);
    }
}
