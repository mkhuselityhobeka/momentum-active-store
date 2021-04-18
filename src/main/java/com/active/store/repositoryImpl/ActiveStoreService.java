package com.active.store.repositoryImpl;

import com.active.store.data.Customer;
import com.active.store.data.Product;
import com.active.store.exceptions.CustomerNotFoundException;
import com.active.store.exceptions.InSufficientActivePointsException;
import com.active.store.exceptions.ProductNotFoundException;
import com.active.store.repositories.CustomerRepository;
import com.active.store.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ActiveStoreService {

    private CustomerRepository customerRepository;
    private ProductRepository  productRepository;
    private List<Product>productList = new ArrayList<> ();

    private Customer customer;
    private int activePoints = 0;
    long customerId;
    int sumOfActivePoints;

    public ActiveStoreService(CustomerRepository customerRepository,ProductRepository  productRepository){
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;

    }

    /* get all products */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /* get customer by id */
    public Customer getCustomerById(long id){
        Optional<Customer>  optionalCustomer = customerRepository.findById (id);
        if (!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException ("Customer with id " + id + " does not exist");
        }
        customer = optionalCustomer.get ();
        return customer;
    }

    /* Check if product exists */
    public boolean getProductById(long id) throws ProductNotFoundException{
        productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException ("Product with id " + id + " does not exist"));
        return true;
    }

    /* Check if product exists from the request list */
    public int customerActivePointsAfterPurchase(List<Long> ids, Pageable pageable) throws InSufficientActivePointsException,ProductNotFoundException{
        Page<Product> productPage = productRepository.findByIdIn(ids,pageable);
        productList = productPage.getContent();
        calculateAcivePoints();
        return activePoints;
    }

    /*Sum of customer points when ordering */
    public void calculateAcivePoints(){

        activePoints = customer.getActivePoints();
        customerId = customer.getId();
        sumOfActivePoints = sumOfProductActivePoints(productList);
        if(activePoints >= sumOfActivePoints){
            activePoints = activePoints - sumOfActivePoints;
            log.debug ("activePoints is ++++++++ " + activePoints);
            updateCustomerPoints(customerId);
        }else {
            throw new InSufficientActivePointsException ("You have inSufficient Points " + "Please use points below " + activePoints);
        }
    }

    /*Sum of product points*/
    public int sumOfProductActivePoints(List<Product>productList){
        int activePointsSum=0;
        for (Product product : productList){
            activePointsSum = activePointsSum + product.getActivePoints();
        }
        return activePointsSum;
    }

    /*Update customer points after successful purchase*/
    public Customer updateCustomerPoints(long id){
        Customer customerUpdate = customerRepository.findById(id)
                .orElseThrow (()-> new CustomerNotFoundException ("The customer id " + id + " does not exist"));
        customerUpdate.setActivePoints(activePoints);
        log.debug ("customerUpdate is " + customerUpdate);
        return customerRepository.save(customerUpdate);
    }

}
