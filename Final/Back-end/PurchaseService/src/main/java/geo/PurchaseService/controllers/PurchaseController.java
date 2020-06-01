package geo.PurchaseService.controllers;

import com.google.gson.Gson;
import geo.PurchaseService.PurchaseValidationException;
import geo.PurchaseService.models.Purchase;
import geo.PurchaseService.models.PurchaseRequest;
import geo.PurchaseService.repos.PurchaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final Gson gson;
    private final Date date = new Date();

    public PurchaseController(PurchaseRepository purchaseRepository, Gson gson) {
        this.purchaseRepository = purchaseRepository;
        this.gson = gson;
    }

    @GetMapping("")
    public ResponseEntity<String> getAllPurchases() {
        return new ResponseEntity<String>(gson.toJson(purchaseRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPurchaseById(@PathVariable Integer id) {
        Purchase purchase = purchaseRepository.getById(id);
        if (purchase == null) {
            throw new PurchaseValidationException("Purchase with id: " + id + " doesn't exist");
        }
        return new ResponseEntity<String>(gson.toJson(purchase), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createPurchase(@RequestBody PurchaseRequest purchase) {
        Purchase validPurchase = Validate(purchase);
        Purchase createdPurchase = purchaseRepository.save(validPurchase);
        return new ResponseEntity<String>(gson.toJson(createdPurchase), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putPurchaseById(@PathVariable Integer id, @RequestBody PurchaseRequest purchase) {
        Purchase findedPurchase = purchaseRepository.getById(id);
        if (findedPurchase == null) {
            throw new PurchaseValidationException("Purchase with id: " + id + " doesn't exist");
        }
        Purchase validPurchase = Validate(purchase);
        findedPurchase.setUser_id(validPurchase.getUser_id());
        findedPurchase.setSum(validPurchase.getSum());
        findedPurchase.setStatus(validPurchase.getStatus());
        findedPurchase.setGoods_ids(validPurchase.getGoods_ids());
        return new ResponseEntity<String>(gson.toJson(purchaseRepository.save(findedPurchase)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseById(@PathVariable Integer id) {
        Purchase purchase = purchaseRepository.getById(id);
        if (purchase == null) {
            throw new PurchaseValidationException("Purchase with id: " + id + " doesn't exist");
        }
        purchaseRepository.delete(purchase);
        return new ResponseEntity<String>("Purchase has been deleted",HttpStatus.OK);
    }

    private Purchase Validate(PurchaseRequest purchase) {
        Purchase result = new Purchase();
        //Validation (Throws ValidationExceptions)
        if (purchase.getUser_id() < 0) {
            throw new PurchaseValidationException("User_id is invalid! User_id shouldn't be less than 0!");
        }
        if (purchase.getSum() <= 0) {
            throw new PurchaseValidationException("Sum is invalid! Sum shouldn't be equal or less than 0");
        }
        if (purchase.getGoods_ids().length() == 0) {
            throw new PurchaseValidationException("Goods list (goods_ids) cannot be blank!");
        }

        result.setGoods_ids(purchase.getGoods_ids());
        result.setUser_id(purchase.getUser_id());
        result.setSum(purchase.getSum());
        result.setStatus("pending");
        return result;
    }
}
