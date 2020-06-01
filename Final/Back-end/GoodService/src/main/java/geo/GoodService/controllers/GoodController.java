package geo.GoodService.controllers;

import com.google.gson.Gson;
import geo.GoodService.models.Good;
import geo.GoodService.models.GoodRequest;
import geo.GoodService.GoodValidationException;
import geo.GoodService.repos.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private Gson gson;


    @GetMapping("")
    public ResponseEntity<String> getAllGoods() {
        return new ResponseEntity<String>(gson.toJson(goodRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGoodById(@PathVariable Integer id) {
        Good good = goodRepository.getById(id);
        if (good == null) {
            throw new GoodValidationException("Good with id: " + id + " doesn't exist");
        }
        return new ResponseEntity<String>(gson.toJson(good), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createGood(@RequestBody GoodRequest good) {
        Good validGood = Validate(good);
        Good createdGood = goodRepository.save(validGood);
        return new ResponseEntity<String>(gson.toJson(createdGood), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putGoodById(@PathVariable Integer id, @RequestBody GoodRequest good) {
        Good findedGood = goodRepository.getById(id);
        if (findedGood == null) {
            throw new GoodValidationException("Good with id: " + id + " doesn't exist");
        }
        Good validGood = Validate(good);
        findedGood.setNaming(validGood.getNaming());
        findedGood.setPrice(validGood.getPrice());
        findedGood.setCountry(validGood.getCountry());
        findedGood.setDescription(validGood.getDescription());
        findedGood.setRate(validGood.getRate());
        return new ResponseEntity<String>(gson.toJson(goodRepository.save(findedGood)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoodById(@PathVariable Integer id) {
        Good good = goodRepository.getById(id);
        if (good == null) {
            throw new GoodValidationException("Good with id: " + id + " doesn't exist");
        }
        goodRepository.delete(good);
        return new ResponseEntity<String>("Good has been deleted", HttpStatus.OK);
    }

    public Good Validate(GoodRequest good) {
        Good result = new Good();
        //Validation (Throws ValidationExceptions)
        if (good.getNaming().length() > 255) {
            throw new GoodValidationException("Naming is invalid! Max naming size is 255 characters!");
        }
        if (good.getPrice() <= 0) {
            throw new GoodValidationException("Price is invalid! Price cannot be less or equal zero!");
        }
        if (!good.getCountry().matches("(([A-Z]+[a-z]{3,10})+( ){1}+(([A-z])+[a-z]{3,10}))|([A-Z]+[a-z]{3,10})")) {
            throw new GoodValidationException("Country is invalid! Valid example: `Ukraine` or `Hawaii Republic`!");
        }
        if (good.getDescription().length() > 255) {
            throw new GoodValidationException("Description is invalid! Max naming size is 255 characters!");
        }
        if (good.getRate() < 0 || good.getRate() > 5) {
            throw new GoodValidationException("Rate is invalid! Rate shouldn't be grater than 5 and less than 0!");
        }

        result.setNaming(good.getNaming());
        result.setCountry(good.getCountry());
        result.setDescription(good.getDescription());
        result.setRate(good.getRate());
        result.setPrice(good.getPrice());

        return result;
    }
}
