package geo.ApiGateway.Comment;

import geo.ApiGateway.Good.Good;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="commentservice")
public interface CommentClient {

    @GetMapping("/comment")
    public ResponseEntity<String> getAllComments();

    @GetMapping("/comment/{id}")
    public ResponseEntity<String> getCommentById(@PathVariable Integer id);

    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestBody Comment comment);

    @PutMapping("/comment/{id}")
    public ResponseEntity<String> putCommentById(@PathVariable Integer id, @RequestBody Comment comment);

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Integer id);


}
