package shop.mtcoding.mallpractice2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Table(name = "product_tb")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String price;
    private Integer qty;
}
