package cn.ccsu;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.ccsu.mapper")
@EnableTransactionManagement
public class GarmentSpringbootApplication {
    public static void main(String[] args){
        SpringApplication.run(GarmentSpringbootApplication.class,args);
    }


}
