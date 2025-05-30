package lk.ijse.cmjd109.LostAndFoundApp.security;

import lk.ijse.cmjd109.LostAndFoundApp.dao.UserDao;
import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserRegisterDao;
import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserRegisterEntity;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRegisterDao userRegisterDao;
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRegisterDao
//                .findByEmail(username)
//                .map(userRegisterEntity ->new User(
//                        userRegisterEntity.getEmail(),
//                        userRegisterEntity.getPassword(),
//                        userRegisterEntity.getAuthorities(),
//                )).orElseThrow(new UserNotFoundException("user not found");

        return userRegisterDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
