package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MainCategoryDTO;

import java.util.List;

public interface MainCategoryService {
    List<MainCategoryDTO> viewAllMainCategory();
    MainCategoryDTO getMainCategoryById(Long mainCategoryId);
    MainCategoryDTO getMainCategoryDetail(Long mainCategoryId);
}