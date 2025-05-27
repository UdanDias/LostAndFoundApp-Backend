package lk.ijse.cmjd109.LostAndFoundApp.dao;

import lk.ijse.cmjd109.LostAndFoundApp.entities.ItemEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository <UserEntity,String>{

}
