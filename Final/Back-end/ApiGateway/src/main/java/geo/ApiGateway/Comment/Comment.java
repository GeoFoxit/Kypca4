package geo.ApiGateway.Comment;

public class Comment {

    private String content;
    private Integer user_id;
    private Integer good_id;

    public Comment() {
    }

    public Comment(String content, Integer user_id, Integer good_id) {
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