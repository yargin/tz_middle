package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.mappers.ItemMapper;
import com.example.yarginy.tz_middle.mappers.TopicHandledMapper;
import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.models.Item;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
public class ItemMapperTest {
    private final ItemMapper itemMapper;
    private final TopicHandledMapper topicMapper;

    @Autowired
    public ItemMapperTest(ItemMapper itemMapper, TopicHandledMapper topicMapper) {
        this.itemMapper = itemMapper;
        this.topicMapper = topicMapper;
    }

    @Test
    public void testInsertAndDeleteItems() {
        Topic topic = new Topic("math", "");
        assertTrue(topicMapper.insert(topic));
        Item item = new Item("test", 1, topic);
        assertTrue(itemMapper.insert(item));
        Collection<Item> items = itemMapper.selectAll();
        assertEquals(1, items.size());
        assertTrue(itemMapper.delete(item));
        items = itemMapper.selectAll();
        assertEquals(0, items.size());
    }

    @Test
    public void testInsertAndDeleteTopics() {
        Topic topic = new Topic("math", "");
        Item item = new Item("item1", 1, topic);
        Item item2 = new Item("item2", 2, topic);
        Collection<Item> items = asList(item, item2);
        topic.setItems(items);
        topicMapper.insert(topic);
        itemMapper.insert(item);
        itemMapper.insert(item2);
        Topic createdTopic = topicMapper.selectTopicById(topic.getId());
        assertEquals(1, topicMapper.selectAll().size());
    }
}
