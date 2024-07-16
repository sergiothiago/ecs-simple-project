package br.com.siecola.aws_project01.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);


    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        log.info("Test Controller - name {} - " + name);

        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/gasOrEtanol/gasprice/{gasPrice}/etanol/{etanolPrice}")
    public ResponseEntity<?> gasOrEtanol(@PathVariable BigDecimal etanolPrice, @PathVariable BigDecimal gasPrice) {
        log.info("Gas or Etanol Compensation - Test Controller - ");
        log.info("Gas price {} " + gasPrice);
        log.info("Etanol price {} " + etanolPrice);

        if( (gasPrice.multiply(BigDecimal.valueOf(0.7)).compareTo(etanolPrice)) > 0 ) {
            return ResponseEntity.ok("Etanol");
        }
        return ResponseEntity.ok("Gas");
    }


}
