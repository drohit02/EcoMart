package com.task.ecomart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.ecomart.customexception.APIException;
import com.task.ecomart.customexception.ResourceNotFoundException;
import com.task.ecomart.dto.CategoryDTO;
import com.task.ecomart.dto.ProductDTO;
import com.task.ecomart.models.Category;
import com.task.ecomart.models.Product;
import com.task.ecomart.repository.CategoryRepository;
import com.task.ecomart.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts(Integer pageNumber, Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPages = this.productRepository.findAll(pages);

        List<Product> products = productPages.getContent();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product List is empty");
        }

        return products.stream().map(this::productToProductDTO).toList();
    }

    public List<ProductDTO> getProductWithCategoryId(Long categoryId, Integer pageNumber, Integer pageSize) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Pageable pageData = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPages = this.productRepository.findAllByCategory(category, pageData);

        List<Product> products = productPages.getContent();
        if (products.isEmpty()) {
            throw new APIException("Product list is empty");
        }

        return products.stream().map(this::productToProductDTO).toList();
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Category category = this.categoryRepository.findByCategoryName(productDTO.getCategory().getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", productDTO.getCategory().getCategoryName()));

        boolean productPresent = category.getProducts().stream()
                .noneMatch(product -> product.getProductName().equals(productDTO.getProductName()));

        if (productPresent) {
            Product product = productDtoToProduct(productDTO);
            product.setCategory(category);
            product.setSpecialPrice(calculateSpecialPrice(product));
            Product savedProduct = this.productRepository.save(product);

            return productToProductDTO(savedProduct);
        } else {
            throw new ResourceNotFoundException("Product is already present in database!!");
        }
    }

    public ProductDTO updateExitingProductData(ProductDTO productDTO, Long productId) {
        Product savedProduct = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Category category = this.categoryRepository.findByCategoryName(productDTO.getCategory().getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryName", productDTO.getCategory().getCategoryName()));

        Product updatedProduct = updateProductData(savedProduct, productDtoToProduct(productDTO));
        updatedProduct.setCategory(category);

        return productToProductDTO(updatedProduct);
    }

    public String deleteProductById(Long productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", productId));

        this.productRepository.deleteById(product.getProductId());
        return "Product Removed Successfully!!!";
    }

    public ProductDTO loadProductById(Long productId) {
        Product savedProduct = this.productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", productId));
        
        return productToProductDTO(savedProduct);
    }

    /*-----------------------------------Utility Methods-----------------------------------------*/

    private double calculateSpecialPrice(Product product) {
        return product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
    }

    private Product updateProductData(Product savedProduct, Product product) {
        savedProduct.setProductName(product.getProductName());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setDiscount(product.getDiscount());
        savedProduct.setQuantity(product.getQuantity());
        savedProduct.setSpecialPrice(calculateSpecialPrice(product));
        savedProduct.setCategory(product.getCategory());
        return this.productRepository.save(savedProduct);
    }

    private ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSpecialPrice(product.getSpecialPrice());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(product.getCategory().getCategoryId());
        categoryDTO.setCategoryName(product.getCategory().getCategoryName());
        productDTO.setCategory(categoryDTO);

        return productDTO;
    }

    private Product productDtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setQuantity(productDTO.getQuantity());
        product.setSpecialPrice(productDTO.getSpecialPrice());
        return product;
    }

}
