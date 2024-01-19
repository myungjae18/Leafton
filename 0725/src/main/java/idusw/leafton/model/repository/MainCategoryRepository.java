package idusw.leafton.model.repository;

import idusw.leafton.model.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainCategoryRepository  extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAll();
    Optional<MainCategory> findById(Long MainCategoryId);
}