package lk.ijse.cmjd109.LostAndFoundApp.dto;

import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Role role;
}
