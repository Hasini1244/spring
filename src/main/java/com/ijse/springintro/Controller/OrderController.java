package com.ijse.springintro.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.Entity.Order;
import com.ijse.springintro.Entity.Item;
import com.ijse.springintro.Service.OrderService;
import com.ijse.springintro.Service.ItemService;
import com.ijse.springintro.dto.OrderDTO;





@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        List<Long> itemIds = orderDTO.getItemIds();

        Order order = new Order(); //initialize new order
        order.setTotalPrice(0.0);
        List<Item> orderedItems = new ArrayList<>(); //initialize orderedProducts array

        itemIds.forEach(itemId -> {
            //get item by Id
            Item item = itemService.getItemById(itemId);
            
            if(item != null) {
                //add item to the order
                orderedItems.add(item);
                order.setTotalPrice(order.getTotalPrice() + item.getPrice());
                //calculate total price
            }
        });
        
        order.setOrderedItems(orderedItems); 
        orderService.createOrder(order); //save new order       
        return ResponseEntity.status(201).body(order);
    }

}