package lk.ijse.cmjd109.LostAndFoundApp.dao;

import lk.ijse.cmjd109.LostAndFoundApp.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository <ItemEntity,String>{

}
