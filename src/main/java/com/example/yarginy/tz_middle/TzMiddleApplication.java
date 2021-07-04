package com.example.yarginy.tz_middle;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import com.example.yarginy.tz_middle.services.TopicService;
import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.impl.HazelcastInstanceImpl;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import static java.util.Arrays.asList;

@SpringBootApplication(scanBasePackages = "com.example.yarginy.tz_middle")
@MappedTypes({Item.class, Topic.class})
@MapperScan("com.example.yarginy.tz_middle.mappers")
@EnableCaching
public class TzMiddleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TzMiddleApplication.class, args);
    }

    @Bean("hazelcastInstance")
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }
}

