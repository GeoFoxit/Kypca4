package geo.ApiGateway.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@FeignClient(value="authservice")
public interface UserClient {

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody User user) throws NoSuchAlgorithmException;

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody User user);

    @GetMapping("/user")
    public ResponseEntity<String> getAll();

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getById(@PathVariable int id);

    @GetMapping("/user/find/{email}")
    public ResponseEntity<String> getIdByEmail(@PathVariable String email);

    @GetMapping("/user/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestHeader(value = "Authorization") String token);

    @GetMapping("/user/isClient")
    public ResponseEntity<Boolean> isClient(@RequestHeader(value = "Authorization") String token);
}