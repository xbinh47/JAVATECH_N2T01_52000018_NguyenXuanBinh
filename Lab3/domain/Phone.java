package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@Entity
@Table(name="Phone")

public class Phone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)

    private Integer id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="price",nullable = false)
    private Integer price;
    @Column(name="color",nullable = false)
    private String color;
    @Column(name="country")
    private String country;
    @Column(name="quantity")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    public Phone() {
        super();
    }

    public Phone(Integer id, String name, Integer price, String color, String country, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
    }

    public Phone(String name, Integer price, String color, String country, Integer quantity) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }
    public String toString() {
        return "Phone name: " + name + ", price: " + price + ", color: " + color +
                ", country: " + country + ", quantity: " + quantity;
    }
}
