package lk.ijse.cmjd109.LostAndFoundApp.service.impl.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserDao;
//import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserRegisterDao;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
//import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.security.jwt.JWTUtils;
import lk.ijse.cmjd109.LostAndFoundApp.service.secure.UserService;
import lk.ijse.cmjd109.LostAndFoundApp.util.EntityDTOConvert;
import lk.ijse.cmjd109.LostAndFoundApp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final JWTUtils jwtUtils;
//    private final EntityDTOConvert entityDTOConvert;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
//    private final UserRegisterDao userRegisterDao;



    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        var userEntity = userDao.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setGender(userDTO.getGender());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());


        // ✅ Hash the password if it's changed
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//        userEntity.setPassword(encodedPassword);
//        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
//            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//            userEntity.setPassword(encodedPassword);
//        }

        userEntity.setRole(userDTO.getRole());

        // ✅ Save the updated user
        userDao.save(userEntity);
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

    @Override
    public JwtAuthResponse signIn(UserLoginDTO signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var userByEmail=userDao.findByEmail(signIn.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        var generatedToken=jwtUtils.generateToken(userByEmail.getUserId(),userByEmail.getEmail(),userByEmail.getAuthorities());
        return JwtAuthResponse.builder().token(generatedToken).build();

    }

    @Override
    public JwtAuthResponse signUp(UserDTO signUp) {



        signUp.setUserId(UtilData.generateUserId());
        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        signUp.setAccountCreatedDate(UtilData.generateTodayDate());
        var savedRegisteredUser=userDao.save(entityDTOConvert.convertUserDTOToUserEntity(signUp));
        var generateToken=jwtUtils.generateToken(savedRegisteredUser.getUserId(), savedRegisteredUser.getEmail(),savedRegisteredUser.getAuthorities());
        return JwtAuthResponse.builder().token(generateToken).build();
    }
}
