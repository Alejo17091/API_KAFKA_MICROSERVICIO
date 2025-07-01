package com.example.microservicio1.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class productcontroller {
    // Inyectamos el KafkaTemplate que nos permite enviar mensajes a Kafka
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Constructor con inyección de dependencia
    public productcontroller(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Endpoint POST que recibe un producto en formato JSON
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody String productJson) {
        // Enviamos el JSON al topic de Kafka llamado "product-topic"
        System.out.println("Enviando a Kafka: " + productJson); // confirmamos envio
        kafkaTemplate.send("product-topic", productJson); // Este debe enviar el mensaje

        // Respondemos al cliente con un mensaje de éxito
        return ResponseEntity.ok("Producto enviado a Kafka");
    }

}
