package com.tads4.ecmmerce.services;

import com.tads4.ecmmerce.dto.ProductDTO;
import com.tads4.ecmmerce.entities.Product;
import com.tads4.ecmmerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
    @Service
    public class ProductService {

        @Autowired
        private ProductRepository repository;

        public ProductDTO findById(Long id){
            Product product = repository.findById(id).get();
            return new ProductDTO(product);
        }

        @Transactional(readOnly = true)
        public Page<ProductDTO> findAll(Pageable pageable){
            Page<Product> product = repository.findAll(pageable);
            return product.map(x -> new ProductDTO(x));
        }


        @Transactional
        public ProductDTO update(Long id, ProductDTO dto){
            Product entity = new Product();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setImgUrl(dto.getImgUrl());
            entity.setPrice(dto.getPrice());

            entity = repository.save(entity);

            return new ProductDTO(entity);
        }

    }
