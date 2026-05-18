package com.trade.app.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TradeLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeLoginServiceApplication.class, args);
	}

}
