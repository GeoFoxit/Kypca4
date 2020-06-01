package geo.ApiGateway.Comment;

import geo.ApiGateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentClient commentClient;
    @Autowired
    UserClient userClient;

    @GetMapping("")
    public ResponseEntity<String> getAllComments() {
        return commentClient.getAllComments();
    };

    @GetMapping("/{id}")
    public ResponseEntity<String> getCommentById(@PathVariable Integer id) {
        return commentClient.getCommentById(id);
    };

    @PostMapping("")
    public ResponseEntity<String> createComment(@RequestBody Comment comment, @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return commentClient.createComment(comment);
    };

    @PutMapping("/{id}")
    public ResponseEntity<String> putCommentById(@PathVariable Integer id, @RequestBody Comment comment, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return commentClient.putCommentById(id,comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Integer id, @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return commentClient.deleteCommentById(id);
    }

}
