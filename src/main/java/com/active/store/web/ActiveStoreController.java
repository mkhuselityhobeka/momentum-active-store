package com.active.store.web;

import com.active.store.data.Customer;
import com.active.store.data.Product;
import com.active.store.repositoryImpl.ActiveStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("momentum/active-store/v1")
public class ActiveStoreController {

    private final ActiveStoreService activeStoreService;

    public ActiveStoreController(ActiveStoreService activeStoreService){
        this.activeStoreService = activeStoreService;

    }

    /*return all products*/
    @Operation(summary = "This will return all products in db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Return the List",
                content = {@Content(mediaType="application/json",schema = @Schema(implementation = Product.class))})})
    @GetMapping("/all/products")
    public ResponseEntity<List<Product>>listOfAllProducts(){
        return new ResponseEntity<> (activeStoreService.getAllProducts(), HttpStatus.OK);
    }

    /*Search custmer by id*/
    @Operation(summary = "This will find customer by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Return the List",
            content = {@Content(mediaType="application/json",schema = @Schema(implementation = Product.class))}),
    @ApiResponse(responseCode = "404",description = "Customer no found",content = @Content),
    @ApiResponse (responseCode = "400",description = "invalid id ",content = @Content)})
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id){
        return new ResponseEntity<> (activeStoreService.getCustomerById (id), HttpStatus.OK);
    }

    /*Calculate customer active points after order and update database*/
    @Operation(summary = "Return number of customer active points after purchase")
    @GetMapping(value = "/products",params = "ids")
    public ResponseEntity<Integer>returnCustomerRemainingPoints(@RequestParam List<Long> ids, Pageable pageable){
        return new ResponseEntity<> (activeStoreService.customerActivePointsAfterPurchase(ids,pageable),HttpStatus.OK);
    }
}
