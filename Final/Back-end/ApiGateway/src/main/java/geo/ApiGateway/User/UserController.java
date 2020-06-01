package geo.ApiGateway.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserClient userClient;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) throws NoSuchAlgorithmException {
        return userClient.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userClient.login(user);
    }

    @GetMapping("")
    public ResponseEntity<String> getAll(@RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return userClient.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable int id, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return userClient.getById(id);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<String> getIdByEmail(@PathVariable String email, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return userClient.getIdByEmail(email);
    }
}
