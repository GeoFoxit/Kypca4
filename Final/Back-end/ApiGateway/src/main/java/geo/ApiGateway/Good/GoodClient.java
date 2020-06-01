package geo.ApiGateway.Good;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value="goodservice")
public interface GoodClient {

    @GetMapping("/good")
    public ResponseEntity<String> getAllGoods();

    @GetMapping("/good/{id}")
    public ResponseEntity<String> getGoodById(@PathVariable Integer id);

    @PostMapping("/good")
    public ResponseEntity<String> createGood(@RequestBody Good good);

    @PutMapping("/good/{id}")
    public ResponseEntity<String> putGoodById(@PathVariable Integer id, @RequestBody Good good);

    @DeleteMapping("/good/{id}")
    public ResponseEntity<String> deleteGoodById(@PathVariable Integer id);

}
