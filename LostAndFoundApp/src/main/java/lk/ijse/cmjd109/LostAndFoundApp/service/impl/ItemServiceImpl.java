package lk.ijse.cmjd109.LostAndFoundApp.service.impl;

import lk.ijse.cmjd109.LostAndFoundApp.dao.ItemDao;
import lk.ijse.cmjd109.LostAndFoundApp.dao.UserDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.ItemDTO;
import lk.ijse.cmjd109.LostAndFoundApp.entities.UserEntity;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.ItemNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.ItemService;
import lk.ijse.cmjd109.LostAndFoundApp.util.EntityDTOConvert;
import lk.ijse.cmjd109.LostAndFoundApp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;
    private final EntityDTOConvert entityDTOConvert;
    private final UserDao userDao;
    @Override
    public void addItem(ItemDTO itemDTO) {

        itemDTO.setItemId(UtilData.generateItemId());
        System.out.println(itemDTO);

        UserEntity userEntity=userDao.findById(itemDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        var itemEntity = entityDTOConvert.convertItemDTOToItemEntity(itemDTO);
        itemEntity.setUser(userEntity);
        itemDao.save(itemEntity);
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        var itemEntity=itemDao.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item not found"));
        itemEntity.setItemName(itemDTO.getItemName());
        itemEntity.setDescription(itemDTO.getDescription());
        itemEntity.setColor(itemDTO.getColor());
        itemEntity.setLocationFound(itemDTO.getLocationFound());
        itemEntity.setItemStatus(itemDTO.getItemStatus());
        itemEntity.setLostDate(itemDTO.getLostDate());

    }

    @Override
    public void deleteItem(String itemId) {
        itemDao.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        itemDao.deleteById(itemId);
    }

    @Override
    public ItemDTO getItem(String itemId) {
        itemDao.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        return entityDTOConvert.convertItemEntityToItemDTO(itemDao.getReferenceById(itemId));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return entityDTOConvert.convertItemEntityListToItemDTOList(itemDao.findAll());
    }
}
