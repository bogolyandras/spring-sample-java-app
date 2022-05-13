package com.bogolyandras.demoapp;

import com.bogolyandras.demoapp.dto.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public MessageDto helloWorld() {
        return new MessageDto("Hello, World! It is a nice day, isn't it?");
    }

}
