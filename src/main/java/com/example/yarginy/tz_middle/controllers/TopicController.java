package com.example.yarginy.tz_middle.controllers;

import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.services.TopicService;
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
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public Collection<Topic> getAll() {
        return topicService.selectAll();
    }

    @PostMapping
    public Topic create(@RequestBody Topic topic) {
        topicService.insert(topic);
        return topic;
    }

    @PutMapping
    public boolean update(@RequestBody Topic topic) {
        return topicService.update(topic);
    }

    @DeleteMapping
    public boolean delete(@RequestBody Topic topic) {
        return topicService.delete(topic);
    }
}
