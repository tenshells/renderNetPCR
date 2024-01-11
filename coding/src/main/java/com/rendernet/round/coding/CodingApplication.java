package com.rendernet.round.coding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rendernet.round.coding.models.CipherDispatcher;
import com.rendernet.round.coding.models.InfinityInterceptor;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
public class CodingApplication {

	@Autowired
	CipherDispatcher cipherDispatcher;
	

	@Autowired
	InfinityInterceptor infinityInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(CodingApplication.class, args);
	}

	@GetMapping("/dispatch")
	public void dispatch(){
		cipherDispatcher.dispatch();
	}


	@GetMapping("/intercept")
	public void intercept(){
		infinityInterceptor.intercept();
	}


}
