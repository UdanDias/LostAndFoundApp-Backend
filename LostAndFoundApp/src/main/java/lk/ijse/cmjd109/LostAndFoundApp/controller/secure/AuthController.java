package lk.ijse.cmjd109.LostAndFoundApp.controller.secure;

import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.JwtAuthResponse;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserLoginDTO;
import lk.ijse.cmjd109.LostAndFoundApp.dto.secure.UserRegisterDTO;
import lk.ijse.cmjd109.LostAndFoundApp.service.secure.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("signin")
    public ResponseEntity<JwtAuthResponse>signIn(UserLoginDTO signIn) {
        return new ResponseEntity<>(authService.signIn(signIn),HttpStatus.OK);
    }
    @PostMapping("signup")
    public ResponseEntity<JwtAuthResponse>signUp(UserRegisterDTO signUp) {
        return new ResponseEntity<>(authService.signUp(signUp),HttpStatus.CREATED);
    }
}
