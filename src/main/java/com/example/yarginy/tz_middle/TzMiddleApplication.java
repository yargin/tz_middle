package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes({Item.class, Topic.class})
@MapperScan("com.example.yarginy.tz_middle.mappers")
public class TzMiddleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TzMiddleApplication.class, args);
    }
}