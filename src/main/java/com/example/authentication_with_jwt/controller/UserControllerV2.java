package com.example.authentication_with_jwt.controller;

import com.example.authentication_with_jwt.custom_exception.*;
import com.example.authentication_with_jwt.entities.Address;
import com.example.authentication_with_jwt.entities.MyUser;
import com.example.authentication_with_jwt.models.*;
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

@RestController
@RequestMapping(value = "/users/v2/")
public class UserControllerV2 {
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

            SignupResponsePayload payload = new SignupResponsePayload(jwt);
            SignupResponseV2 signupResponseV2 = new SignupResponseV2(true, payload);
            return ResponseEntity.status(200).body(signupResponseV2);
        } catch (UserAlreadyException e) {
            return new ApiResponse<>().createResponse(new SignupApiResponsePayload("User Already exists"));
        }
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            final UserDetails userDetails = userDetailService.checkUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());
            final String jwt = jwtUtil.generateToken(userDetails);

            LoginResponsePayload payload = new LoginResponsePayload(jwt);

            LoginResponseV2 loginResponse = new LoginResponseV2(true, payload);
            return ResponseEntity.status(200).body(loginResponse);
        } catch (UsernameNotFoundException error) {
            return new ApiResponse<>().createResponse(new ApiResponsePayload("Username not found"));
        } catch (BadCredentialsException error) {
            return new ApiResponse<>().createResponse(new LoginApiResponsePayload("Password is incorrect"));
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
