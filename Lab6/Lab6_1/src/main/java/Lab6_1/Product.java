package Lab6_1;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String description;

    public Product() {
        super();
    }

    public Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
        this.description = product.description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
