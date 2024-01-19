package idusw.leafton.model.repository;

import idusw.leafton.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCart_CartIdAndProduct_ProductId(Long CartId, Long ProductId);

    Optional<CartItem> findById(Long cartItemId);
}