package midterm.application.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orderDetails"})
public class Product {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "color", columnDefinition = "VARCHAR(255) DEFAULT 'white'", nullable = false)
    private String color;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "image", nullable = false)
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Category category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OrderDetail> orderDetails;
}
