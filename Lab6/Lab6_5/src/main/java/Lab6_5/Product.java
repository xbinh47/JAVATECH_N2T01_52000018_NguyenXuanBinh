package Lab6_5;

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

    public Product(Integer id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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
