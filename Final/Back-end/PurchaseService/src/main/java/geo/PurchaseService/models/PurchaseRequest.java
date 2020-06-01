package geo.PurchaseService.models;

public class PurchaseRequest {

    private Integer user_id;
    private Double sum;
    private String goods_ids;

    public PurchaseRequest() {

    }

    public PurchaseRequest(Integer user_id, Double sum, String goods_ids) {
        this.user_id = user_id;
        this.sum = sum;
        this.goods_ids = goods_ids;
    }

    public String getGoods_ids() {
        return goods_ids;
    }
    public void setGoods_ids(String goods_ids) {
        this.goods_ids = goods_ids;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Double getSum() {
        return sum;
    }
    public void setSum(Double sum) {
        this.sum = sum;
    }
}
