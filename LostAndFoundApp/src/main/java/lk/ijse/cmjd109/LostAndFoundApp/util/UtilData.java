package lk.ijse.cmjd109.LostAndFoundApp.util;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class UtilData {
    public static String generateItemId(){
        return"I/"+ UUID.randomUUID();
    }
    public static String generateRequestId(){
        return"R/"+ UUID.randomUUID();
    }
    public static String generateUserId(){
        return"U/"+ UUID.randomUUID();
    }
    public static LocalDate generateTodayDate(){
        return LocalDate.now();
    }
    public static Time generateCurrentTime(){
        return Time.valueOf(LocalTime.now());
    }
    public static String generateUserRegisterId(){
        return "UR/"+ UUID.randomUUID();
    }
}
