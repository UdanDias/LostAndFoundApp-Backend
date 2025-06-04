package lk.ijse.cmjd109.LostAndFoundApp.dao;

import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.RequestStatus;
import lk.ijse.cmjd109.LostAndFoundApp.entities.RequestEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RequestDao extends JpaRepository <RequestEntity,String>{
    List<RequestEntity> findByUserUserId(String userId);


    @Query("SELECT COUNT(r) FROM RequestEntity r WHERE r.user.userId = :userId")
    long countTotalRequestsByUserId(@Param("userId") String userId);

    // Count active requests for a specific user
    @Query("SELECT COUNT(r) FROM RequestEntity r WHERE r.user.userId = :userId AND r.isActiveRequest = true")
    long countActiveRequestsByUserId(@Param("userId") String userId);

    // Count requests by status for a specific user
    @Query("SELECT COUNT(r) FROM RequestEntity r WHERE r.user.userId = :userId AND r.requestStatus = :status")
    long countByRequestStatusAndUserId(@Param("userId") String userId, @Param("status") RequestStatus status);



}
