package lk.ijse.cmjd109.LostAndFoundApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.cmjd109.LostAndFoundApp.dto.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO implements Serializable {
    private String requestId;
    private String itemId;
    private String userId;
    private RequestStatus requestStatus;
    private Boolean isActiveRequest;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate requestedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time requestedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time updatedTime;
    private String reward;
}


