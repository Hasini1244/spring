package com.ijse.springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.springintro.Entity.Item;



@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
