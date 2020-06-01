package geo.ApiGateway.Purchase;

import geo.ApiGateway.Good.Good;
import geo.ApiGateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseClient purchaseClient;
    @Autowired
    UserClient userClient;

    @GetMapping("")
    public ResponseEntity<String> getAllPurchases() {
        return purchaseClient.getAllPurchases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPurchaseById(@PathVariable Integer id) {
        return purchaseClient.getPurchaseById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> createPurchase(@RequestBody Purchase purchase, @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return purchaseClient.createPurchase(purchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> patchPurchaseById(@PathVariable Integer id, @RequestBody Purchase purchase, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return purchaseClient.putPurchaseById(id, purchase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseById(@PathVariable Integer id, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return purchaseClient.deletePurchaseById(id);
    }

}
