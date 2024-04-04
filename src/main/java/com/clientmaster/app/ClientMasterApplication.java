package com.clientmaster.app;

import com.clientmaster.app.client.entity.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientMasterApplication.class, args);

		Client client = new Client();
	}


}
