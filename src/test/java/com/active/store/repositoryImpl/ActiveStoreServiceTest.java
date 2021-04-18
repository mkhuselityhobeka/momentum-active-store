package com.active.store.repositoryImpl;

import com.active.store.data.Customer;
import com.active.store.data.Product;
import com.active.store.repositories.CustomerRepository;
import com.active.store.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ActiveStoreServiceTest {

    @Autowired
    private ActiveStoreService activeStoreService;
    @MockBean
    private ProductRepository productRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Customer customer;
    @Mock
    private Product product;

    @Test
    public void testFindCustomerById(){
        doReturn(Optional.of(customer)).when (customerRepository).findById (1L);
        Customer customer = activeStoreService.getCustomerById(1L);
        Assertions.assertNotNull(customer);
    }

    @Test
    public void testGetAllProducts(){

        Product product = new Product (1L,8);
        Product product1 = new Product (1L,9);
        doReturn(Arrays.asList(product,product1)).when(productRepository).findAll ();
        List<Product> products = activeStoreService.getAllProducts();
        Assertions.assertEquals(2, products.size());

    }
    @Test
    public void testGetProductById(){
        long id = 1L;
        doReturn(Optional.of(product)).when (productRepository).findById(id);
        boolean productFound = activeStoreService.getProductById(id);
        Assertions.assertTrue(productFound);
    }

}
