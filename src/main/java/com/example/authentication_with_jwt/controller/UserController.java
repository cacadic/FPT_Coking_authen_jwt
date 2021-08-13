package com.example.authentication_with_jwt.controller;

import com.example.authentication_with_jwt.custom_exception.UserIdNotFound;
import com.example.authentication_with_jwt.entities.Address;
import com.example.authentication_with_jwt.entities.MyUser;
import com.example.authentication_with_jwt.models.LoginRequest;
import com.example.authentication_with_jwt.models.LoginResponse;
import com.example.authentication_with_jwt.models.SignUpRequest;
import com.example.authentication_with_jwt.services.AddressService;
import com.example.authentication_with_jwt.services.JwtUtil;
import com.example.authentication_with_jwt.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.authentication_with_jwt.custom_exception.UserAlreadyException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/users/")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello() {
        return "Correct username password. Welcome";
    }

    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String goodbye() {
        return "Welcome from hello1";
    }

    @ExceptionHandler(UserAlreadyException.class)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signupRequest) {
        try {
            Address address = addressService.getAddress(signupRequest.getWard(), signupRequest.getDistrict(), signupRequest.getCity());
            UserDetails userDetails = userDetailService.signUp(signupRequest.getUsername(), signupRequest.getPassword(), address);
            final String jwt = jwtUtil.generateToken(userDetails);
            LoginResponse signupResponse = new LoginResponse(jwt, "Signup thành công");
            return ResponseEntity.status(200).body(signupResponse);
        } catch (UserAlreadyException e) {
            LoginResponse signupResponse = new LoginResponse(e.getMessage());
            return ResponseEntity.status(500).body(signupResponse);
        }
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            final UserDetails userDetails = userDetailService.checkUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());
            final String jwt = jwtUtil.generateToken(userDetails);

            LoginResponse loginResponse = new LoginResponse(jwt, "Login thanh cong");
            return ResponseEntity.status(200).body(loginResponse);
        } catch (UsernameNotFoundException error) {
            LoginResponse loginResponse = new LoginResponse("", error.getMessage());
            return ResponseEntity.status(500).body(loginResponse);
        } catch (BadCredentialsException error) {
            LoginResponse loginResponse = new LoginResponse("", error.getMessage());
            return ResponseEntity.status(500).body(loginResponse);
        }
    }

    @ExceptionHandler({UserIdNotFound.class})
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfor(@RequestParam Integer userId) {
        try {
            MyUser user = this.userDetailService.getUserById(userId);
            return ResponseEntity.status(200).body(user);
        } catch (UserIdNotFound err) {
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }
}
