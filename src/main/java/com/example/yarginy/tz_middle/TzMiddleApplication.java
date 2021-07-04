package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.services.TopicService;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.util.Arrays.asList;

@SpringBootApplication
@MappedTypes({Item.class, Topic.class})
@MapperScan("com.example.yarginy.tz_middle.mappers")
@ComponentScan("com.example.yarginy.tz_middle")
public class TzMiddleApplication implements CommandLineRunner {
    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TzMiddleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Topic topic = new Topic("testTpic", "desc");
        Item item1 = new Item("item1", 1, topic);
        Item item2 = new Item("item2", 2, topic);
        topic.setItems(asList(item1, item2));
        topicService.insert(topic);

        topic = new Topic("testTopic2", "desc2");
        item1 = new Item("item11", 1, topic);
        item2 = new Item("item22", 2, topic);
        topic.setItems(asList(item1, item2));
        topicService.insert(topic);

        System.out.println(topicService.selectTopicById(1).getItems());
        System.out.println(topicService.selectTopicById(2).getItems());
    }
}

