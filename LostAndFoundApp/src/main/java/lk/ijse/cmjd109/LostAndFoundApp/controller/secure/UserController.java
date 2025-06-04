package lk.ijse.cmjd109.LostAndFoundApp.controller.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
//import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.secure.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @DeleteMapping("deleteuser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>deleteUser(@RequestParam("userId")String userId){
        if(userId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }catch(UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "updateuser", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void>updateUser(@RequestParam("userId")String userId,@RequestBody UserDTO userDTO){
        if(userId==null||userDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.updateUser(userId,userDTO);
            return ResponseEntity.noContent().build();
        }catch(UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getuser")

    public ResponseEntity<UserDTO>getUser(@RequestParam("userId")String userId){
        if (userId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(userService.getUser(userId));
        }catch(UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getallusers")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")

    public ResponseEntity<List<UserDTO>> getAllUsers(){

        try {
            return ResponseEntity.ok(userService.getAllUsers());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("signin")
    public ResponseEntity<JwtAuthResponse>signIn(@RequestBody UserLoginDTO signIn) {
        return new ResponseEntity<>(userService.signIn(signIn),HttpStatus.OK);
    }
    @PostMapping("signup")

    public ResponseEntity<JwtAuthResponse>signUp(@RequestBody UserDTO signUp) {
        return new ResponseEntity<>(userService.signUp(signUp),HttpStatus.CREATED);
    }

}
