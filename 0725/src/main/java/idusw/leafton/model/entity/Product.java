package idusw.leafton.model.entity;

import idusw.leafton.model.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {
    @Id // pk를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long productId;

    @ManyToOne
    @JoinColumn (name = "mainCategoryId") //fk지정
    private MainCategory mainCategory;

    @OneToOne
    @JoinColumn (name = "mainMaterialId")//fk 지정
    private MainMaterial mainMaterial;

    @OneToOne
    @JoinColumn (name = "styledId") //fk 지정
    private Style style;

    @Column
    private String name;

    @Column
    private String color;

    @Column
    private String size;

    @Column
    private Integer price;

    @Column
    private Integer salePercentage;

    @Column
    private Integer weight;

    @Column
    private Integer asPeriod;

    @Column
    private String registDate;

    @Column
    private Integer amount;

    @Column
    private Integer isAssemble;

    @Column
    private String content;

    @Column
    private String mainImage;

    @Column
    private String subImage;

    @Column
    private String thumbImage;

    public static Product toProductEntity(ProductDTO productDTO){

        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setMainCategory(MainCategory.toMainCategoryEntity(productDTO.getMainCategoryDTO()));
        product.setMainMaterial(MainMaterial.toMainMaterialEntity(productDTO.getMainMaterialDTO()));
        //product.setCompany(productDTO.getCompany());
        //product.setImage(productDTO.getImage());
        product.setStyle(Style.toStyleEntity(productDTO.getStyleDTO()));
        product.setName(productDTO.getName());
        product.setColor(productDTO.getColor());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        product.setSalePercentage(productDTO.getSalePercentage());
        product.setWeight(productDTO.getWeight());
        product.setAsPeriod(productDTO.getAsPeriod());
        product.setRegistDate(productDTO.getRegistDate());
        product.setAmount(productDTO.getAmount());
        product.setIsAssemble(productDTO.getIsAssemble());
        product.setContent(productDTO.getContent());

        return product;
    }
}
