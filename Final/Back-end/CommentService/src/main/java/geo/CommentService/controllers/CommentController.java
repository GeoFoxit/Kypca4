package geo.CommentService.controllers;

import com.google.gson.Gson;
import geo.CommentService.CommentValidationException;
import geo.CommentService.models.Comment;
import geo.CommentService.models.CommentRequest;
import geo.CommentService.repos.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final Gson gson;

    public CommentController(CommentRepository commentRepository, Gson gson) {
        this.commentRepository = commentRepository;
        this.gson = gson;
    }

    @GetMapping("")
    public ResponseEntity<String> getAllComments() {
        return new ResponseEntity<String>(gson.toJson(commentRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCommentById(@PathVariable Integer id) {
        Comment comment = commentRepository.getById(id);
        if (comment == null) {
            throw new CommentValidationException("Comment with id: " + id + " doesn't exist");
        }
        return new ResponseEntity<String>(gson.toJson(comment), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createComment(@Valid @RequestBody CommentRequest comment) {
        Comment validComment = Validate(comment);
        Comment createdComment = commentRepository.save(validComment);
        return new ResponseEntity<String>(gson.toJson(createdComment), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCommentById(@PathVariable Integer id, @Valid @RequestBody CommentRequest comment) {
        Comment findedComment = commentRepository.getById(id);
        if (findedComment == null) {
            throw new CommentValidationException("Comment with id: " + id + " doesn't exist");
        }
        Comment validComment = Validate(comment);
        findedComment.setContent(validComment.getContent());
        findedComment.setUser_id(validComment.getUser_id());
        findedComment.setGood_id(validComment.getGood_id());
        return new ResponseEntity<String>(gson.toJson(commentRepository.save(findedComment)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Integer id) {
        Comment comment = commentRepository.getById(id);
        if (comment == null) {
            throw new CommentValidationException("Comment with id: " + id + " doesn't exist");
        }
        commentRepository.delete(comment);
        return new ResponseEntity<String>("Comment has been deleted",HttpStatus.OK);
    }

    private Comment Validate(CommentRequest comment) {
        Comment result = new Comment();
        //Validation (Throws ValidationExceptions)
        if (comment.getContent().length() > 255) {
            throw new CommentValidationException("Content is invalid! Max comment content size is 255 characters!");
        }
        if (comment.getUser_id() < 0) {
            throw new CommentValidationException("User_id is invalid! User_id shouldn't be less than 0!");
        }
        if (comment.getGood_id() < 0) {
            throw new CommentValidationException("Good_id is invalid! Good_id shouldn't be less than 0!");
        }

        result.setContent(comment.getContent());
        result.setGood_id(comment.getGood_id());
        result.setUser_id(comment.getUser_id());
        return result;
    }
}
