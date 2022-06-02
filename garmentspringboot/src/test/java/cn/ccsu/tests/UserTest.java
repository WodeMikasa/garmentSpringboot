package cn.ccsu.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserTest {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Test
    public void test01(){
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
    }
    @Test
    public void test(){
        String s="123456";
        String b="123456";
        System.out.println(s.equals(b));
    }
}
