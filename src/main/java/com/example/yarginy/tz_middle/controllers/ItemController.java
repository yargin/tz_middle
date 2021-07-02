package com.example.yarginy.tz_middle.controllers;

import com.example.yarginy.tz_middle.mappers.ItemMapper;
import com.example.yarginy.tz_middle.models.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemMapper itemMapper;

    public ItemController(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @GetMapping()
    public Collection<Item> selectAll() {
        return itemMapper.selectAll();
    }

    @PostMapping
    public Item create(Item item) {
        itemMapper.insert(item);
        return item;
    }
}
