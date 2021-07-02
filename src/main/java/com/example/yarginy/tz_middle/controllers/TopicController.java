package com.example.yarginy.tz_middle.controllers;

import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Topic;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicMapper topicMapper;

    public TopicController(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @GetMapping
    public Collection<Topic> getAll() {
        return topicMapper.selectAll();
    }

    @PostMapping
    public Topic create(@RequestBody Topic topic) {
        topicMapper.insert(topic);
        return topic;
    }

    @PutMapping
    public boolean update(@RequestBody Topic topic) {
        return topicMapper.update(topic);
    }

    @DeleteMapping
    public boolean delete(@RequestBody Topic topic) {
        return topicMapper.delete(topic);
    }
}
