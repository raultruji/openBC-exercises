package com.exercise.obcex789;

import com.exercise.obcex789.entities.Laptop;
import com.exercise.obcex789.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObcEx789Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObcEx789Application.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null,"intel",8,800.50,false );
		Laptop laptop2 = new Laptop(null,"amd",8,950.00,true );
		Laptop laptop3 = new Laptop(null,"amd",16,1980.00,true );

		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
		laptopRepository.save(laptop3);

		System.out.println(laptopRepository.count());
	}

}
