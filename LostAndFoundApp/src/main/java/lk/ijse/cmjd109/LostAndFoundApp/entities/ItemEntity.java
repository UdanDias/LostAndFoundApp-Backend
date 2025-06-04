package lk.ijse.cmjd109.LostAndFoundApp.entities;

import jakarta.persistence.*;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.ItemStatus;
import lk.ijse.cmjd109.LostAndFoundApp.entities.secure.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemId;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;
    private String itemName;
    private String description;
    private String color;
    private String locationFound;
//    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
    private String lostDate;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestEntity>requests;
}
