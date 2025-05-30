package lk.ijse.cmjd109.LostAndFoundApp.service.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;

public interface AuthService {
    JwtAuthResponse signIn(UserLoginDTO signIn);
    JwtAuthResponse signUp(UserRegisterDTO signUp);
}
