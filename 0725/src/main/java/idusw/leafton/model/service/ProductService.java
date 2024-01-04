package idusw.leafton.model.service;

import idusw.leafton.model.DTO.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService { //ProductService 구현도
    List<ProductDTO> viewProducts(); //List

}