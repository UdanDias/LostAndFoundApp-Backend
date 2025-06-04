package lk.ijse.cmjd109.LostAndFoundApp.entities.secure;

import jakarta.persistence.*;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.Role;
import lk.ijse.cmjd109.LostAndFoundApp.entities.ItemEntity;
import lk.ijse.cmjd109.LostAndFoundApp.entities.RequestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable, UserDetails {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate accountCreatedDate;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestEntity>requests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }


    @Override
    public String getUsername() {
        return email;
    }
}
