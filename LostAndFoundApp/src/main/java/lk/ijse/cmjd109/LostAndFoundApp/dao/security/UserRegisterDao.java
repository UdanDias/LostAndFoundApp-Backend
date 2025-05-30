package lk.ijse.cmjd109.LostAndFoundApp.dao.security;

import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegisterDao extends JpaRepository<UserRegisterEntity, String> {
    Optional <UserRegisterEntity> findByEmail(String email);
}
