package com.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springboot")
@EntityScan("com.springboot")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
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



