package com.bawantha.ecom.service;

import com.bawantha.ecom.model.Product;
import com.bawantha.ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
