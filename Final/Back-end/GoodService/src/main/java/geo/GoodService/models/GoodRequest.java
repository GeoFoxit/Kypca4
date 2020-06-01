package geo.GoodService.models;

public class GoodRequest {
    private String naming;
    private Double price;
    private String country;
    private String description;
    private Integer rate;

    public GoodRequest() {}

    public GoodRequest(String naming, Double price, String country, String description, Integer rate) {
        this.naming = naming;
        this.price = price;
        this.country = country;
        this.description = description;
        this.rate = rate;
    }

    public String getNaming() {
        return naming;
    }
    public void setNaming(String naming) {
        this.naming = naming;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getRate() {
        return rate;
    }
    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
