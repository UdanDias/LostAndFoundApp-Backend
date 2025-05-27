package lk.ijse.cmjd109.LostAndFoundApp.service;

import lk.ijse.cmjd109.LostAndFoundApp.dto.UserDTO;

import java.util.List;

public interface UserService {
    void addUser(UserDTO userDTO);
    void updateUser(String userId,UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO getUser(String userId);
    List<UserDTO> getAllUsers();
}
