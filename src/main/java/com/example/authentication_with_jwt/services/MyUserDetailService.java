package com.example.authentication_with_jwt.services;

import com.example.authentication_with_jwt.entities.Address;
import com.example.authentication_with_jwt.entities.MyUser;
import com.example.authentication_with_jwt.entities.Role;
import com.example.authentication_with_jwt.repositories.AddressRepository;
import com.example.authentication_with_jwt.repositories.UserRepository;
import com.example.authentication_with_jwt.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.authentication_with_jwt.custom_exception.UserAlreadyException;
import com.example.authentication_with_jwt.custom_exception.UserIdNotFound;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            MyUser user = userRepository.findByUsername(username);
            return new User("use1", "123", new ArrayList<>());
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            throw e;
        }
    }

    public CustomUserDetails signUp(String username, String password, Address address) {
        try {
            MyUser user = userRepository.findByUsername(username);
            if (user != null) {
                throw new UserAlreadyException("Đã có user này tồn tại");
            }

            Set<Address> addresses = new HashSet<Address>();
            addresses.add(address);

            MyUser newUser = new MyUser(username, password);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setAddress(addresses);
            Role role = roleRepository.findByTitle("customer");
            newUser.setRole(role);
            userRepository.save(newUser);
            return new CustomUserDetails(newUser);
        } catch (Exception error) {
            throw error;
        }
    }

    public CustomUserDetails checkUsernamePassword(String username, String password) {
        try {
            MyUser user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Username not found");
            }
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Password incorrect");
            }

            return new CustomUserDetails(user);
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            throw e;
        }
    }

    public UserDetails getUserById(Integer userId) {
        try {
            Optional<MyUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserIdNotFound("User with id " + userId + " not found");
            }
            return new CustomUserDetails(user.get());
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            throw e;
        }
    }
}
