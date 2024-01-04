package idusw.leafton.model.service;

import idusw.leafton.model.DTO.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idusw.leafton.model.entity.Product;
import idusw.leafton.model.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService { //ProductService를 구현도를 받고 implements를 구현함

    @Autowired
    private final ProductRepository productRepository; //객체의 상태를 저장하고, 해당 상태를 클래스 내의 여러 메서드에서 공유하거나 조작하기 위해 필드 선언

//    public ProductServiceImpl(ProductRepository productRepository) {
//        //ProductServiceImpl은 ProductService를 의존하고 있어 종속성을 관리하기 위해 종속객체를 주입함
//        //ProductRepository 매개변수를 받아 내부 필드인 prouductRepository 에 할당
//
//        this.productRepository = productRepository; //필드와 매개변수 충돌을 해결하기 위해 this 활용, ProductServiceImpl 특정구현체에 의존하지 않게됨
//    }

    @Override
    public List<ProductDTO> viewProducts() { // 그래서 재정의함

        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>(); //productDTO로 ArrayList 객체만듬
        for(Product product: productList) {
            productDTOList.add(ProductDTO.toProductDTO(product)); //productDTO 객체안에 DTO 데이터 넣음
        }
        return productDTOList;
    }
}