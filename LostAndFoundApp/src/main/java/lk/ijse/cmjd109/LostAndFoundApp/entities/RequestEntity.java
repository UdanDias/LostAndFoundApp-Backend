package lk.ijse.cmjd109.LostAndFoundApp.entities;

import jakarta.persistence.*;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "request")
public class RequestEntity {
    @Id
    private String requestId;
    @ManyToOne
    @JoinColumn(name = "itemId",nullable = false)
    private ItemEntity item;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;
    private RequestStatus requestStatus;
    private Boolean isActiveRequest;
    private LocalDate requestedDate;
    private Time requestedTime;
    private String reward;
}
