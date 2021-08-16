package com.example.authentication_with_jwt.controller;

import com.example.authentication_with_jwt.custom_exception.*;
import com.example.authentication_with_jwt.entities.Address;
import com.example.authentication_with_jwt.entities.MyUser;
import com.example.authentication_with_jwt.models.*;
import com.example.authentication_with_jwt.services.AddressService;
import com.example.authentication_with_jwt.services.CustomUserDetails;
import com.example.authentication_with_jwt.services.JwtTokenProvider;
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
    private JwtTokenProvider jwtTokenProvider;

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
            CustomUserDetails userDetails = userDetailService.signUp(signupRequest.getUsername(), signupRequest.getPassword(), address);

            SignupResponse signupResponse = new SignupResponse(true);
            return ResponseEntity.status(200).body(signupResponse);
        } catch (UserAlreadyException e) {
            return new ApiResponse<>().createResponse(new SignupApiResponsePayload("User Already exists"));
        }
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            final CustomUserDetails userDetails = userDetailService.checkUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());

            final String jwt = jwtTokenProvider.generateToken(userDetails);

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
            UserDetails user = userDetailService.getUserById(userId);
            return ResponseEntity.status(200).body(user);
        } catch (UserIdNotFound err) {
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }
}
