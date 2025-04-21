package sg.edu.nus.team3.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import sg.edu.nus.team3.shoppingcart.model.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ShoppingCartItem s WHERE s.id = :cartId")
    public void deleteCartItemById(@Param("cartId") int cartId);
}
