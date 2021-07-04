package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.services.TopicService;
import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.impl.HazelcastInstanceImpl;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import static java.util.Arrays.asList;

@SpringBootApplication(scanBasePackages = "com.example.yarginy.tz_middle")
@MappedTypes({Item.class, Topic.class})
@MapperScan("com.example.yarginy.tz_middle.mappers")
@EnableCaching
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
    public void run(String... args) {
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

    @Bean("hazelcastInstance")
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }
}

