package lk.ijse.cmjd109.LostAndFoundApp.dao;

import lk.ijse.cmjd109.LostAndFoundApp.entities.RequestEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDao extends JpaRepository <RequestEntity,String>{

}
