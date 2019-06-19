package com.gilang.retail.service;

import com.gilang.retail.model.request.ItemRequest;
import com.gilang.retail.model.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService{

    ItemResponse itemResponse;
    HashMap<String, ItemResponse> items;

    public ItemService(){

        ItemResponse itemResponsea = new ItemResponse();

        itemResponsea.setItemId("1");
        String itemId = itemResponsea.getItemId();

        itemResponsea.setItemName("Baju");
        itemResponsea.setItemPrice(100000);

        if(items == null){
            items = new HashMap<>();
        }

        items.put(itemId, itemResponsea);

        ItemResponse itemResponse1 = new ItemResponse();

        itemResponse1.setItemId("2");
        String itemId2 = itemResponse1.getItemId();

        itemResponse1.setItemName("Celana");
        itemResponse1.setItemPrice(70000);

        items.put(itemId2, itemResponse1);

        ItemResponse itemResponse2 = new ItemResponse();

        itemResponse2.setItemId("3");
        String itemId3 = itemResponse2.getItemId();

        itemResponse2.setItemName("Sandal");
        itemResponse2.setItemPrice(12000);

        items.put(itemId3, itemResponse2);
    }

    public ItemResponse createItem(ItemRequest itemRequest){
        itemResponse = new ItemResponse();

        itemResponse.setItemId(itemRequest.getItemId());
        String itemId = itemResponse.getItemId();

        itemResponse.setItemName(itemRequest.getItemName());
        itemResponse.setItemPrice(itemRequest.getItemPrice());

        items.put(itemId, itemResponse);

        return itemResponse;
    }


    public Collection<ItemResponse> showAllItem(){
        return items.values();
    }

    public ItemResponse getById(String itemId){
        return items.get(itemId);
    }

    public ItemResponse updateItem(String itemId, ItemResponse itemResponse){
        if (items.containsKey(itemId)){
            ItemResponse itemTemp = items.get(itemId);

            itemTemp.setItemName(itemResponse.getItemName());
            itemTemp.setItemPrice(itemResponse.getItemPrice());

            items.put(itemId, itemTemp);
        }

        return items.get(itemId);
    }

    public ItemResponse deleteItem(String itemId){
        return items.remove(itemId);
    }

}
