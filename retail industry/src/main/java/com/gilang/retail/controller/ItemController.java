package com.gilang.retail.controller;

import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.ItemRequest;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/showitem")
    public ResponseEntity showAllItem(){
        Collection itemsu = itemService.showAllItem();
        return new ResponseEntity<>(itemsu, HttpStatus.OK);
    }

    @GetMapping("/showitem/{itemId}")
    public ResponseEntity getById(@PathVariable("itemId") String itemId){
        ItemResponse itemValue = itemService.getById(itemId);
        if (itemValue != null){
            return new ResponseEntity<>(itemValue,HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }

    }

    @PostMapping("/create")
    public ResponseEntity createItem(@Valid @RequestBody ItemRequest itemRequest){
        ItemResponse itemValue = itemService.createItem(itemRequest);
        return new ResponseEntity<>(itemValue,HttpStatus.CREATED);
    }

    @PatchMapping("/update/{itemId}")
    public ResponseEntity updateItem(@PathVariable("itemId") String itemId, @Valid @RequestBody ItemResponse itemResponse){
        ItemResponse itemValue = itemService.updateItem(itemId, itemResponse);
        if (itemValue != null){
            return new ResponseEntity<>(itemValue,HttpStatus.OK);
        } else {

            throw new DataNotFoundException("Item Not Found");
        }
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity deleteItem(@PathVariable("itemId") String itemId){
        ItemResponse itemValue = itemService.deleteItem(itemId);
        if (itemValue != null) {
            return new ResponseEntity<>(itemValue,HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Item Not Found");
        }

    }
}
