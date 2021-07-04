package com.example.yarginy.tz_middle.services;

import com.example.yarginy.tz_middle.mappers.ItemMapper;
import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Service
public class ItemService {
    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public Collection<Item> selectByTopic(Topic topic) {
        return itemMapper.selectByTopicId(topic.getId());
    }

    public Collection<Item> selectAll() {
        return itemMapper.selectAll();
    }

    public boolean insert(Item item) {
        return itemMapper.insert(item);
    }

    public boolean insert(Collection<Item> items) {
        return items == null || itemMapper.insertAll(items);
    }

    public boolean update(Item item) {
        return itemMapper.update(item);
    }

    public boolean update(Collection<Item> items) {
        return itemMapper.updateAll(items);
    }

    public boolean update(Collection<Item> storedItems, Collection<Item> newItems) {
        Collection<Item> itemsToAdd = new ArrayList<>();
        Collection<Item> itemsToUpdate = new ArrayList<>();
        newItems.forEach(i -> {
            if (i.getId() == 0) {
                itemsToAdd.add(i);
            } else {
                itemsToUpdate.add(i);
            }
        });
        boolean inserted = itemsToAdd.isEmpty() || itemMapper.insertAll(itemsToAdd);
        boolean updated = itemsToUpdate.isEmpty() || itemMapper.updateAll(itemsToUpdate);

        Collection<Item> itemsToDelete = new HashSet<>(storedItems);
        itemsToDelete.removeAll(newItems);
        boolean deleted = itemsToDelete.isEmpty() || itemMapper.deleteAll(itemsToDelete);
        return inserted || updated || deleted;
    }

    public boolean delete(Item item) {
        return itemMapper.delete(item);
    }

    public boolean delete(Collection<Item> items) {
        return itemMapper.deleteAll(items);
    }
}
