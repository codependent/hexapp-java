package com.codependent.hexapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@SpringBootTest
class HexappApplicationTests {
    
    private final ApplicationContext applicationContext;

    HexappApplicationTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

}
