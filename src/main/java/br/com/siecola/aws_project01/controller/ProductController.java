package br.com.siecola.aws_project01.controller;

import br.com.siecola.aws_project01.model.Product;
import br.com.siecola.aws_project01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Iterable<Product> getAllProducts() {return productRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@RequestParam Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productSaved = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product productForUpdate) {
        Optional<Product> OldProduct = productRepository.findById(productForUpdate.getId());
        if(OldProduct.isPresent()){
            Product productUpdated = productRepository.save(productForUpdate);
            return ResponseEntity.ok(productUpdated);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bycode/{code}")
    public ResponseEntity<Product> findByCode(@RequestParam String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
