package geo.CommentService.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotBlank(message = "Content cannot be blank")
    private String content;
    @NotNull(message = "User id cannot be null")
    private Integer user_id;
    @NotNull(message = "Good id cannot be null")
    private Integer good_id;

    public CommentRequest() {
    }

    public CommentRequest(@NotBlank String content, Integer user_id, Integer good_id) {
        this.content = content;
        this.user_id = user_id;
        this.good_id = good_id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Integer getGood_id() {
        return good_id;
    }
    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }
}
