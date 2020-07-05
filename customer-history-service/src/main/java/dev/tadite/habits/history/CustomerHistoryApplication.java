package dev.tadite.habits.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class CustomerHistoryApplication {

    public static void main(String[] args){
        Hooks.onOperatorDebug();
//        BlockHound.install();
        SpringApplication.run(CustomerHistoryApplication.class, args);
    }
}
