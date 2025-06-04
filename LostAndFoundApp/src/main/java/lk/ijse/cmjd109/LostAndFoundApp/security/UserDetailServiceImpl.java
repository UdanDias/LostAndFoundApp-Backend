package lk.ijse.cmjd109.LostAndFoundApp.security;

import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserDao;
//import lk.ijse.cmjd109.LostAndFoundApp.dao.security.UserRegisterDao;
//import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserRegisterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
//    private final UserRegisterDao userRegisterDao;
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//

        return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
