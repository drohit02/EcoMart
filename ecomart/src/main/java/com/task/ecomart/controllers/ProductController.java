package com.task.ecomart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.ecomart.dto.ProductDTO;
import com.task.ecomart.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        List<ProductDTO> products = this.productService.getAllProducts(pageNumber, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> fetchProductById(@PathVariable(name = "id") Long productId){
    	ProductDTO productDTO = this.productService.loadProductById(productId);
    	return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.OK);
    } 


    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryId) {
        ProductDTO savedProductDTO = this.productService.addProduct(productDTO, categoryId);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProductData(@RequestBody ProductDTO productDTO, @PathVariable(name="id") Long productId) {
        ProductDTO updatedProductDTO = this.productService.updateExitingProductData(productDTO, productId);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> removeProductById(@PathVariable(name="id")  Long productId) {
        String deleteStatus = this.productService.deleteProductById(productId);
        return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
    }

}
