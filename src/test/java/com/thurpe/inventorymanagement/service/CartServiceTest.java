package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Cart;
import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.domain.Product;
import com.thurpe.inventorymanagement.dto.AddToCartDTO;
import com.thurpe.inventorymanagement.dto.CartDTO;
import com.thurpe.inventorymanagement.dto.CartItemDTO;
import com.thurpe.inventorymanagement.repository.CartRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @InjectMocks
    private CartService cartService;
    @Mock
    private CartRepo cartRepo;

    private Cart cart = new Cart();
    private Customer customer = new Customer();
    private Product product = new Product();
    private AddToCartDTO addToCartDTO = new AddToCartDTO();

    @BeforeEach
    void setup(){
        product.setName("name");
        product.setPrice(20.0);

        addToCartDTO.setQuantity(1);

        cart = new Cart(product, addToCartDTO.getQuantity(), customer);
    }

    @Test
    void addToCart(){
        cartService.addToCart(addToCartDTO, product, customer);

        verify(cartRepo, times(1)).save(argThat(savedCart->{
            assertNotNull(savedCart.getProduct());
            assertNotNull(savedCart.getCustomer());
            assertEquals(addToCartDTO.getQuantity(), savedCart.getQuantity());
            return true;
        }));
    }

    @Test
    void listCartItems(){
        when(cartRepo.findAllByCustomerOrderByCreatedDateDesc(any()))
                .thenReturn(List.of(cart));

        CartDTO actual = cartService.listCartItems(customer);

        assertNotNull(actual);
        assertEquals(20.0, actual.getTotalCost());
    }

    @Test
    void getDTOFromCart(){
        CartItemDTO actual = CartService.getDTOFromCart(cart);
        assertNotNull(actual);
    }
}
