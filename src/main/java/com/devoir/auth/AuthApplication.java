package com.devoir.auth;

import com.devoir.auth.models.User;
import com.devoir.auth.services.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {
    @Autowired
    UserJpaRepository userJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Just for test
        /*User user = userJpaRepository.save(User.builder().email("haithamoumerzoug31@gmaiL.com")
                .name("Haitham OUMERZOUG")
                .password("123456789")
                .phone("0642509795")
                .build());
        System.out.println(user);*/
    }
}
