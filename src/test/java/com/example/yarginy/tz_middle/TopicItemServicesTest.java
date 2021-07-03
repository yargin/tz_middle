package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.services.ItemService;
import com.example.yarginy.tz_middle.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
public class TopicItemServicesTest {
    private final ItemService itemService;
    private final TopicService topicService;
    private final Topic topic = new Topic("math", "");
    private final Item item1 = new Item("item1", 1, topic);
    private final Item item2 = new Item("item2", 2, topic);

    @Autowired
    public TopicItemServicesTest(ItemService itemService, TopicService topicService) {
        this.itemService = itemService;
        this.topicService = topicService;
        Collection<Item> items = asList(item1, item2);
        topic.setItems(items);
    }

    @BeforeEach
    public void init() {
        topicService.delete(topic);
        topic.setId(0);
        item1.setId(0);
        item2.setId(0);
    }

    @Test
    public void testInsertAndDeleteItems() {
        Topic topic = new Topic("math", "");
        assertTrue(topicService.insert(topic));
        Item item = new Item("test", 1, topic);
        assertTrue(itemService.insert(item));
        Collection<Item> items = itemService.selectAll();
        assertEquals(1, items.size());
        assertTrue(itemService.delete(item));
        items = itemService.selectAll();
        assertEquals(0, items.size());
    }

    @Test
    public void testInsertAndDeleteTopics() {
        assertTrue(topicService.insert(topic));
        Collection<Topic> topics = topicService.selectAll();
        assertEquals(1, topics.size());
        Topic created = topicService.selectTopicById(topic.getId());
        assertEquals(created.getItems(), itemService.selectAll());
    }

    @Test
    public void testUpdateTopicAndItsItems() {
        Collection<Item> items = asList(item1, item2);
        topic.setItems(items);
        topicService.insert(topic);

        Topic createdTopic = topicService.selectTopicById(topic.getId());
        createdTopic.setName("updatedName");
        createdTopic.getItems().forEach(i -> i.setName("updatedName"));
        topicService.update(createdTopic);

        Topic updatedTopic = topicService.selectTopicById(topic.getId());
        assertEquals(createdTopic, updatedTopic);
    }
}
