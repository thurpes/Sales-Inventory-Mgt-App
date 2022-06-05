package com.thurpe.inventorymanagement.util;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.utils.Helper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HelperTest {

    @Test
    void notNullOptionalEmpty(){
        Optional<Category> category = Optional.empty();
        assertFalse(Helper.notNull(category));
    }
    @Test
    void notNullOptionalTrue(){
        Optional<Category> category = Optional.of(new Category());
        assertTrue(Helper.notNull(category));
    }

    @Test
    void notNull(){
        Object object = null;
        assertFalse(Helper.notNull(object));
    }
    @Test
    void notNullTrue(){
        assertTrue(Helper.notNull(new Object()));
    }
}
