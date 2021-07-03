package com.example.yarginy.tz_middle.services;

import com.example.yarginy.tz_middle.mappers.ItemMapper;
import com.example.yarginy.tz_middle.models.Item;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemService {
    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
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

    public boolean delete(Item item) {
        return itemMapper.delete(item);
    }
}
