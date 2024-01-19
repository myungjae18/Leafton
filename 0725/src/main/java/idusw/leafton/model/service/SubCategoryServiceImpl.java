package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MainCategoryDTO;
import idusw.leafton.model.DTO.SubCategoryDTO;
import idusw.leafton.model.entity.MainCategory;
import idusw.leafton.model.entity.SubCategory;
import idusw.leafton.model.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService{
    private final SubCategoryRepository subCategoryRepository;
    @Override
    public List<SubCategoryDTO> getSubCategoryByMainCategoryId(MainCategoryDTO mainCategoryDTO){
        List<SubCategory> subCategoryList = subCategoryRepository.findAllByMainCategory(MainCategory.toMainCategoryEntity(mainCategoryDTO));
        List<SubCategoryDTO> subCategoryDTOList = new ArrayList<>();
        for(SubCategory subCategory: subCategoryList) {
            subCategoryDTOList.add(SubCategoryDTO.toSubCategoryDTO(subCategory));  //dto리스트에 reviwe entity 리스트 추가
        }
        return subCategoryDTOList;
    }

    @Override
    public SubCategoryDTO getSubCategoryDetail(Long subCategoryId){
        Optional<SubCategory> opSubCategory = subCategoryRepository.findById(subCategoryId);
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        if (opSubCategory.isPresent()){
            SubCategory subCategory = opSubCategory.get();
            subCategoryDTO = SubCategoryDTO.toSubCategoryDTO(subCategory);
            // findById로 받아온 결과값들을 DTO에 저장함
        }
        else {
            throw new IllegalArgumentException("해당 ID의 상품이 없습니다. ID: " + subCategoryDTO.getSubCategoryId());
        }
        return subCategoryDTO;
    }

    @Override
    public SubCategoryDTO getSubCategoryById(Long subCategoryId){
        Optional<SubCategory> subCategory = subCategoryRepository.findById(subCategoryId);
        if(subCategory.isPresent()) {
            return SubCategoryDTO.toSubCategoryDTO(subCategory.get());
        } else {
            throw new IllegalArgumentException("Invalid subCategory Id: " + subCategoryId);
        }
    }
}