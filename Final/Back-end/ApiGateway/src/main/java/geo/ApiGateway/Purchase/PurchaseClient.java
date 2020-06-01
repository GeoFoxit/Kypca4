package geo.ApiGateway.Purchase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="purchaseservice")
public interface PurchaseClient {

    @GetMapping("/purchase")
    public ResponseEntity<String> getAllPurchases();

    @GetMapping("/purchase/{id}")
    public ResponseEntity<String> getPurchaseById(@PathVariable Integer id);

    @PostMapping("/purchase")
    public ResponseEntity<String> createPurchase(@RequestBody Purchase purchase);

    @PutMapping("/purchase/{id}")
    public ResponseEntity<String> putPurchaseById(@PathVariable Integer id, @RequestBody Purchase purchase);

    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<String> deletePurchaseById(@PathVariable Integer id);


}
