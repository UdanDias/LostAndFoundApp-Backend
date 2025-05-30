package lk.ijse.cmjd109.LostAndFoundApp.dto.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDTO implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
