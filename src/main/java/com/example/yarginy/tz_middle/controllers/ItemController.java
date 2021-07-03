package com.example.yarginy.tz_middle.controllers;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.services.ItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public Collection<Item> selectAll() {
        return itemService.selectAll();
    }

    @PostMapping
    public Item create(Item item) {
        itemService.insert(item);
        return item;
    }

    @PutMapping
    public Boolean update(Item item) {
        return itemService.update(item);
    }

    @DeleteMapping
    public Boolean delete(Item item) {
        return itemService.delete(item);
    }
}
