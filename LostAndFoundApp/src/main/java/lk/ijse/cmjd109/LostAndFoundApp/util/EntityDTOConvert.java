package lk.ijse.cmjd109.LostAndFoundApp.util;

import lk.ijse.cmjd109.LostAndFoundApp.dto.ItemDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.RequestDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserDTO;
//import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.entities.ItemEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.RequestEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserEntity;
//import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserRegisterEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDTOConvert {
    private final ModelMapper modelMapper;
    public ItemEntity convertItemDTOToItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public ItemDTO convertItemEntityToItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public List<ItemDTO>convertItemEntityListToItemDTOList(List<ItemEntity> itemEntityList) {
        return modelMapper.map(itemEntityList,new TypeToken<List<ItemDTO>>(){}.getType());
    }


    public RequestEntity convertRequestDTOToRequestEntity(RequestDTO requestDTO) {
        return modelMapper.map(requestDTO, RequestEntity.class);
    }
    public RequestDTO convertRequestEntityToRequestDTO(RequestEntity requestEntity) {
        return modelMapper.map(requestEntity, RequestDTO.class);
    }
    public List<RequestDTO> convertRequestEntityListToRequestDTOList(List<RequestEntity> requestEntityList) {
        return modelMapper.map(requestEntityList,new TypeToken<List<RequestDTO>>(){}.getType());
    }


    public UserEntity convertUserDTOToUserEntity(UserDTO userDTO) {

        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO convertUserEntityToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> convertUserEntityListToUserDTOList(List<UserEntity>userEntityList){
        return modelMapper.map(userEntityList,new TypeToken<List<UserDTO>>(){}.getType());
    }

//    public UserRegisterEntity convertUserRegisterDTOToUserRegisterEntity(UserRegisterDTO userRegisterDTO) {
//        return modelMapper.map(userRegisterDTO, UserRegisterEntity.class);
//    }
//    public UserRegisterDTO convertUserRegisterEntityToUserRegisterDTO(UserRegisterEntity userRegisterEntity) {
//        return modelMapper.map(userRegisterEntity, UserRegisterDTO.class);
//    }
}
