package com.rendernet.round.coding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rendernet.round.coding.models.CipherDispatcher;
import com.rendernet.round.coding.models.InfinityInterceptor;
import com.rendernet.round.coding.models.PairInfiniteInterceptors;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
public class CodingApplication {

	@Autowired
	CipherDispatcher cipherDispatcher;
	
	@Autowired
	InfinityInterceptor infinityInterceptor;

	@Autowired
	PairInfiniteInterceptors infiniteInterceptorPair;

	public static void main(String[] args) {
		SpringApplication.run(CodingApplication.class, args);
	}

	@GetMapping("/dispatch")
	public void dispatch(String jsonBody){
		cipherDispatcher.dispatch(jsonBody);
	}


	@GetMapping("/intercept")
	public void intercept(){
		infinityInterceptor.intercept();
	}



	@GetMapping("/interceptPair")
	public void interceptPair(){
		infiniteInterceptorPair.startPairIntercept();
	}



}
