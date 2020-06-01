package geo.ApiGateway.Good;

import geo.ApiGateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping("/good")
public class GoodController {
    @Autowired
    GoodClient goodClient;
    @Autowired
    UserClient userClient;

    @GetMapping("")
    public ResponseEntity<String> getAllGoods() {
        return goodClient.getAllGoods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGoodById(@PathVariable Integer id) {
        return goodClient.getGoodById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> createGood(@RequestBody Good good, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return goodClient.createGood(good);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> patchGoodById(@PathVariable Integer id, @RequestBody Good good, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return goodClient.putGoodById(id, good);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoodById(@PathVariable Integer id, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return goodClient.deleteGoodById(id);
    }

}
