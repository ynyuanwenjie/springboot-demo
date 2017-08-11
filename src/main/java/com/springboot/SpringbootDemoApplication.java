package com.springboot;

import com.springboot.model.Reservation;
import com.springboot.service.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.springboot")
@EntityScan("com.springboot")
public class SpringbootDemoApplication {

	/*@Bean
    CommandLineRunner runner (ReservationRepository rr) {
		return  args -> {
			System.out.println("spring boot start inti params .....");
			Arrays.asList("jlong,Lee,ada,neo,yuanwenjie".split(",")).forEach(
					s -> rr.save(new Reservation(s))
			);
			rr.findAll().stream().forEach(s -> System.out.println(s));
		};
	}*/

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
}

