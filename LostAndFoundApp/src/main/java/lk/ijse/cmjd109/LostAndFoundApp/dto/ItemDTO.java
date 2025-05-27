package lk.ijse.cmjd109.LostAndFoundApp.dto;

import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements Serializable {
    private String itemId;
    private String userId;
    private String itemName;
    private String description;
    private String color;
    private String locationFound;
    private ItemStatus itemStatus;
    private String lostDate;

}
