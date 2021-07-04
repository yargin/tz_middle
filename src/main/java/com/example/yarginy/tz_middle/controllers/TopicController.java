package com.example.yarginy.tz_middle.controllers;

import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.services.TopicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Collection<Topic> create(@RequestBody Topic topic) {
        topicService.insert(topic);
        return topicService.selectAll();
    }

    @PutMapping(produces = {"application/json"})
    public Collection<Topic> update(@RequestBody Topic topic) throws JsonProcessingException {
        topicService.update(topic);
        return topicService.selectAll();
    }

    @DeleteMapping
    public Collection<Topic> delete(@RequestParam Integer id) {
        topicService.delete(id);
        return topicService.selectAll();
    }
}
