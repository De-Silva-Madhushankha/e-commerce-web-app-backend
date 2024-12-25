package com.bawantha.ecom.service;

import com.bawantha.ecom.model.Product;
import com.bawantha.ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //orElse() or get() method is used to avoid null pointer exception
    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        try {
            product.setImageData(image.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productRepo.save(product);
    }
}
