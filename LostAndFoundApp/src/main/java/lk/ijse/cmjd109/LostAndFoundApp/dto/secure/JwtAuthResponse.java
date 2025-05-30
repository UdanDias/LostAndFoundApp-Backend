package lk.ijse.cmjd109.LostAndFoundApp.dto.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtAuthResponse implements Serializable {
    private String token;
}
