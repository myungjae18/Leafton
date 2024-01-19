package idusw.leafton.model.repository;

import idusw.leafton.model.entity.MainCategory;
import idusw.leafton.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findAllByMainCategory(MainCategory mainCategory);
    Optional<SubCategory> findById(Long subCategoryId);
}