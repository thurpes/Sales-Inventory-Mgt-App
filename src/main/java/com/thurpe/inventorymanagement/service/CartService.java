package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Cart;
import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.domain.Product;
import com.thurpe.inventorymanagement.dto.AddToCartDTO;
import com.thurpe.inventorymanagement.dto.CartDTO;
import com.thurpe.inventorymanagement.dto.CartItemDTO;
import com.thurpe.inventorymanagement.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    public CartService(){}

    public CartService(CartRepo cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDTO addToCartDTO, Product product, Customer customer){
        Cart cart = new Cart(product, addToCartDTO.getQuantity(), customer);
        cartRepository.save(cart);
    }


    public CartDTO listCartItems(Customer customer) {
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        List<CartItemDTO> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDTO cartItemDTO = getDTOFromCart(cart);
            cartItems.add(cartItemDTO);
        }
        int totalCost = 0;
        for (CartItemDTO cartItemDTO :cartItems){
            totalCost += (cartItemDTO.getProduct().getPrice()* cartItemDTO.getQuantity());
        }
        return new CartDTO(cartItems, totalCost);
    }


    public static CartItemDTO getDTOFromCart(Cart cart) {
        return new CartItemDTO(cart);
    }

    public void updateCartItem(AddToCartDTO cartDTO, Customer customer, Product product){
        Cart cart = cartRepository.getOne(cartDTO.getId());
        cart.setQuantity(cartDTO.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id, Long customerId) {
        if (!cartRepository.existsById(id))
            throw new IllegalArgumentException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(Long customerId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(Customer customer) {
        cartRepository.deleteByCustomer(customer);
    }
}
