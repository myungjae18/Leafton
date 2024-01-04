package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Long productId;

    private MainCategoryDTO mainCategoryDTO;

    private MainMaterialDTO mainMaterialDTO;

    //private Company company;

    //private Image image;

    private StyleDTO styleDTO;

    private String name;

    private String color;

    private String size;

    private Integer price;

    private Integer salePercentage;

    private Integer weight;

    private Integer asPeriod;

    private String registDate;

    private Integer amount;

    private Integer isAssemble;

    private String content;

    private String mainImage;

    private String subImage;

    private String thumbImage;

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(product.getProductId());
        productDTO.setMainCategoryDTO(MainCategoryDTO.toMainCategoryDTO(product.getMainCategory()));
        productDTO.setMainMaterialDTO(MainMaterialDTO.toMainMaterialDTO(product.getMainMaterial()));
        //productDTO.setCompany(product.getCompany());
        //productDTO.setImage(product.getImage());
        productDTO.setStyleDTO(StyleDTO.toStyleDTO(product.getStyle()));
        productDTO.setName(product.getName());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setPrice(product.getPrice());
        productDTO.setSalePercentage(product.getSalePercentage());
        productDTO.setWeight(product.getWeight());
        productDTO.setAsPeriod(product.getAsPeriod());
        productDTO.setRegistDate(product.getRegistDate());
        productDTO.setAmount(product.getAmount());
        productDTO.setIsAssemble(product.getIsAssemble());
        productDTO.setContent(product.getContent());

        return productDTO;
    }

}
