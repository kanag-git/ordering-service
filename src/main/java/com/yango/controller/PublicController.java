package com.yango.controller;

import com.yango.message.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PublicController {

    private final MessageProducer messageProducer;

    public PublicController(final MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/public")
    public ResponseEntity<Void> publicAccess() throws InterruptedException {
        int counter = 0;
        while(true){
            counter++;
            messageProducer.sendMessage("test-topic", String.valueOf(counter));

            Thread.sleep(3000);
        }
    }
}
