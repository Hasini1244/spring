package com.ijse.springintro.Entity;

import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDateTime;

    private Double totalPrice;

    @PrePersist //execute write before the entity is created
    protected void onCreate() {
        this.orderDateTime = LocalDateTime.now();
/* 
        if(this.totalPrice == null) {
            this.totalPrice = 0.0;
        } */
    }



    @ManyToMany
    @JoinTable(
        name = "order_item",
        joinColumns = @JoinColumn(name = "orderId"),
        inverseJoinColumns = @JoinColumn(name = "itemId") 
    )
    private List<Item> orderedItems;
}