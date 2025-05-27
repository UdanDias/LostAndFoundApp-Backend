package lk.ijse.cmjd109.LostAndFoundApp.service;

import lk.ijse.cmjd109.LostAndFoundApp.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO itemDTO);
    void updateItem(String itemId,ItemDTO itemDTO);
    void deleteItem(String itemId);
    ItemDTO getItem(String itemId);
    List<ItemDTO> getAllItems();
}
