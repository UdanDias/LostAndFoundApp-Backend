package lk.ijse.cmjd109.LostAndFoundApp.service.impl.secure;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.LostAndFoundApp.dao.UserDao;
import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserRegisterDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.security.jwt.JWTUtils;
import lk.ijse.cmjd109.LostAndFoundApp.service.secure.AuthService;
import lk.ijse.cmjd109.LostAndFoundApp.util.EntityDTOConvert;
import lk.ijse.cmjd109.LostAndFoundApp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final JWTUtils jwtUtils;
    private final EntityDTOConvert entityDTOConvert;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRegisterDao userRegisterDao;

    @Override
    public JwtAuthResponse signIn(UserLoginDTO signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
                var userByEmail=userRegisterDao.findByEmail(signIn.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
                var generatedToken=jwtUtils.generateToken(userByEmail.getEmail(),userByEmail.getAuthorities());
                return JwtAuthResponse.builder().token(generatedToken).build();

    }

    @Override
    public JwtAuthResponse signUp(UserRegisterDTO signUp) {



        signUp.setUserId(UtilData.generateUserRegisterId());
        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        var savedRegisteredUser=userRegisterDao.save(entityDTOConvert.convertUserRegisterDTOToUserRegisterEntity(signUp));
        var generateToken=jwtUtils.generateToken(savedRegisteredUser.getEmail(),savedRegisteredUser.getAuthorities());
        return JwtAuthResponse.builder().token(generateToken).build();
    }
}
