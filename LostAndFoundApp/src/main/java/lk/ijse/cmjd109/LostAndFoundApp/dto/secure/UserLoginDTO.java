package lk.ijse.cmjd109.LostAndFoundApp.dto.secure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO implements Serializable {
    private String email;
    private String password;
}
