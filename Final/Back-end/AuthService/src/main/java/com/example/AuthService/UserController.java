package com.example.AuthService;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path="/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody User user) throws NoSuchAlgorithmException {
        if (user.getName() != null && user.getPassword() != null && user.getEmail() != null) {
            return new ResponseEntity<String>(userService.register(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid request!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return new ResponseEntity<String>(userService.login(user), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<String>(gson.toJson(userRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getById(@PathVariable Integer id) {
        return new ResponseEntity<String>(gson.toJson(userRepository.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/user/find/{email}")
    public ResponseEntity<String> getByEmail(@PathVariable String email) {
        return new ResponseEntity<String>(gson.toJson(userRepository.getByEmail(email)),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/isAdmin")
    public ResponseEntity<Boolean> isAdmin() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/user/isClient")
    public ResponseEntity<Boolean> isClient() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


}
