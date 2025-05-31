package lk.ijse.cmjd109.LostAndFoundApp.controller;

import lk.ijse.cmjd109.LostAndFoundApp.dto.UserDTO;
import lk.ijse.cmjd109.LostAndFoundApp.exceptions.UserNotFoundException;
import lk.ijse.cmjd109.LostAndFoundApp.service.UserService;
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO){
        if(userDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.addUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping
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
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping
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
    @PreAuthorize("hasRole('ADMIN')")

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

}
