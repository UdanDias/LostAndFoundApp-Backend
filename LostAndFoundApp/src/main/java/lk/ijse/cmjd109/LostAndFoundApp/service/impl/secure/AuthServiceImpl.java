package lk.ijse.cmjd109.LostAndFoundApp.service.impl.secure;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.service.secure.AuthService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtAuthResponse signIn(UserLoginDTO signIn) {
        return null;
    }

    @Override
    public JwtAuthResponse signUp(UserRegisterDTO signUp) {
        return null;
    }
}
