package lk.ijse.cmjd109.LostAndFoundApp.dto.secure;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate accountCreatedDate;
    private String phoneNumber;
    private String password;
    private Role role;
}
