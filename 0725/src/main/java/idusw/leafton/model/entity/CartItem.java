package idusw.leafton.model.entity;

import idusw.leafton.model.DTO.CartItemDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;

    @Column
    private int count;

    public void addCount(int count){
        this.count = count;
    }

    public static CartItem toCartItemEntity(CartItemDTO cartItemDTO){
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemDTO.getCartItemId());
        cartItem.setCart(Cart.toCartEntity(cartItemDTO.getCartDTO()));
        cartItem.setProduct(cartItem.getProduct());
        cartItem.setCount(cartItem.getCount());

        return cartItem;
    }
}