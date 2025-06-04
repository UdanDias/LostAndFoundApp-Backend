package lk.ijse.cmjd109.LostAndFoundApp.service.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
//import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;

import java.util.List;

public interface UserService {

    void updateUser(String userId,UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO getUser(String userId);
    List<UserDTO> getAllUsers();
    JwtAuthResponse signIn(UserLoginDTO signIn);
    JwtAuthResponse signUp(UserDTO signUp);
}
