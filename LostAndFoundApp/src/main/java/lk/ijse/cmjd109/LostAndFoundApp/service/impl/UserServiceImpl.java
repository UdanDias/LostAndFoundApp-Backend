package lk.ijse.cmjd109.LostAndFoundApp.service.impl;

import lk.ijse.cmjd109.LostAndFoundApp.dao.UserDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.UserDTO;
import lk.ijse.cmjd109.LostAndFoundApp.entities.UserEntity;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.UserService;
import lk.ijse.cmjd109.LostAndFoundApp.util.EntityDTOConvert;
import lk.ijse.cmjd109.LostAndFoundApp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final EntityDTOConvert entityDTOConvert;
    private final ModelMapper modelMapper;

    @Override
    public void addUser(UserDTO userDTO) {
        userDTO.setUserId(UtilData.generateUserId());
        System.out.println(userDTO);
        UserEntity userEntity=entityDTOConvert.convertUserDTOToUserEntity(userDTO);
        userDao.save(userEntity);
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        var userEntity=userDao.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setRole(userDTO.getRole());
    }

    @Override
    public void deleteUser(String userId) {
        userDao.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        userDao.deleteById(userId);

    }

    @Override
    public UserDTO getUser(String userId) {
        userDao.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        return entityDTOConvert.convertUserEntityToUserDTO(userDao.getReferenceById(userId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return entityDTOConvert.convertUserEntityListToUserDTOList(userDao.findAll());
    }
}
