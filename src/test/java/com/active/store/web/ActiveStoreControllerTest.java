package com.active.store.web;

import com.active.store.data.Customer;
import com.active.store.data.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActiveStoreControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate testRestTemplate;



    @Test
    void listOfAllProducts() {
        List<Product> productList = new ArrayList<> ();
      ResponseEntity responseEntity = testRestTemplate.getForEntity("http://localhost:"+port+
                                     "/momentum/active-store/v1/all/products/",
                                     productList.getClass());
      assertEquals (200,responseEntity.getStatusCodeValue ());
    }

    @Test
    void findCustomerById() {
         String url = "http://localhost:"+port+
                 "/momentum/active-store/v1/customer/"+1;
         ResponseEntity<Customer>responseEntity = testRestTemplate.getForEntity (url, Customer.class);
         assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    void returnCustomerRemainingPoints(){
        List<Long>list= new ArrayList<> ();
        list.add (1L);
        list.add (2L);
        long id=1;
        String url = "http://localhost:"+port+
                "/momentum/active-store/v1/purchase/products/ByIds?="+list+"&="+0+"&=0&"+0+"&=id";
        ResponseEntity <Object>responseEntity = testRestTemplate.getForEntity (url, Object.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
