package com.ijse.springintro.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springintro.Entity.Item;



@Service
public interface ItemService {
    List<Item> getAllItems();
    Item createItem(Item item);
    Item getItemById(Long id);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
}