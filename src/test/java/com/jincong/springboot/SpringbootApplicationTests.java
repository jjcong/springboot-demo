package com.jincong.springboot;
import com.github.houbb.sensitive.core.api.SensitiveUtil;
import com.jincong.springboot.config.DCLSingletonTest;
import com.jincong.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringbootApplication.class)
@Slf4j
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello World!");
    }



    @Test
    void testSingleton() {
        User client1 = DCLSingletonTest.getClient();
        User client2 = DCLSingletonTest.getClient();

        log.info("client1", client1);
        log.info("client2", client2);
    }


    @Test
    void testSensitive() {
        User originalUser = new User();
        originalUser.setUserName("张三");
        originalUser.setPassword("123456");
        originalUser.setUserCode("U10001");

        log.info("脱敏前：{}", originalUser);
        log.info("脱敏后：{}", SensitiveUtil.desCopy(originalUser));
    }

}
