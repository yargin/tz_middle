package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.mappers.ItemMapper;
import com.example.yarginy.tz_middle.mappers.TopicMapper;
import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.models.Item;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
public class ItemMapperTest {
    private final ItemMapper itemMapper;
    private final TopicMapper topicMapper;

    @Autowired
    public ItemMapperTest(ItemMapper itemMapper, TopicMapper topicMapper) {
        this.itemMapper = itemMapper;
        this.topicMapper = topicMapper;
    }

    @Test
    public void testInsertValues() {
        Topic topic = new Topic("math", "");
        assertTrue(topicMapper.insert(topic));
        Item item = new Item("test", 1, topic);
        assertTrue(itemMapper.insert(item));
        assertEquals(1, itemMapper.selectAll().size());
        assertTrue(itemMapper.delete(item));
        assertEquals(0, itemMapper.selectAll().size());
    }
}
