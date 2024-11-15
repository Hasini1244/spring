package com.ijse.springintro.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.Entity.Category;
import com.ijse.springintro.Entity.Item;
import com.ijse.springintro.Service.CategoryService;
import com.ijse.springintro.Service.ItemService;
import com.ijse.springintro.dto.ItemReqDTO;


@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();

        return ResponseEntity.status(200).body(items);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody ItemReqDTO itemReqDTO) {
        try {
           Item newItem = new Item();
            newItem.setName(itemReqDTO.getName());
            newItem.setPrice(itemReqDTO.getPrice());
            newItem.setDescription(itemReqDTO.getDescription());
            
            Category category = categoryService.getCategoryById(itemReqDTO.getCategoryId());

            newItem.setCategory(category);
            
            Item createdItem = itemService.createItem(newItem);
            return ResponseEntity.status(201).body(createdItem);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<Item> updateItem(@RequestBody ItemReqDTO itemReqDTO, @PathVariable Long itemId) {

        try {
            Item existingItem = itemService.getItemById(itemId);
            if (existingItem != null) {
                existingItem.setName(itemReqDTO.getName());
                existingItem.setPrice(itemReqDTO.getPrice());
                existingItem.setDescription(itemReqDTO.getDescription());
                
                Category category = categoryService.getCategoryById(itemReqDTO.getCategoryId());
                existingItem.setCategory(category);
                
                Item updatedItem = itemService.updateItem(itemId, existingItem);
                return ResponseEntity.status(200).body(updatedItem);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
       itemService.deleteItem(itemId);
        return ResponseEntity.status(200).body("Item Deleted");
    }
}