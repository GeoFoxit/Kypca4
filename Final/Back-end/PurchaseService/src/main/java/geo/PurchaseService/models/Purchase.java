package geo.PurchaseService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer user_id;
    private Double sum;
    private String status;
    private String goods_ids;

    public Purchase() {
    }

    public Purchase(Integer user_id, Double sum, String status, String goods_ids) {
        this.user_id = user_id;
        this.sum = sum;
        this.status = status;
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
    public void setUser_id(Integer user_id) {this.user_id = user_id;}
    public Double getSum() {
        return sum;
    }
    public void setSum(Double sum) {
        this.sum = sum;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
