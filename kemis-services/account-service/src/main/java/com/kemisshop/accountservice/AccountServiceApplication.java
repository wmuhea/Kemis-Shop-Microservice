package com.kemisshop.accountservice;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.dto.request.AccountTypeForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemisshop.accountservice.app.port.in.AccountRegistrationOrUpdateServiceAccessPort;
import com.kemisshop.accountservice.app.port.in.AccountsManagementServiceAccessPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import java.io.File;
import java.util.List;



import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
public class AccountServiceApplication implements CommandLineRunner {

	private final AccountsManagementServiceAccessPort managementServiceAccessPort;
	private final AccountRegistrationOrUpdateServiceAccessPort registerOrUpdateService;
	private int count;

	public AccountServiceApplication(
			AccountsManagementServiceAccessPort managementServiceAccessPort,
			AccountRegistrationOrUpdateServiceAccessPort registerOrUpdateService) {

		this.managementServiceAccessPort = managementServiceAccessPort;
		this.registerOrUpdateService = registerOrUpdateService;
		this.count = 0;
	}




	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populateAccountTypeTable();
		populateUserTable();

	}

	private void populateAccountTypeTable() {
		AccountTypeForm buyerForm = new AccountTypeForm("Buyer");
		AccountTypeForm sellerForm = new AccountTypeForm("Seller");
		AccountTypeForm adminForm = new AccountTypeForm("Admin");

		managementServiceAccessPort.createAccountType(buyerForm);
		managementServiceAccessPort.createAccountType(sellerForm);
		managementServiceAccessPort.createAccountType(adminForm);
	}

	private void populateUserTable()  throws Exception{
		/*createUsers(importedFile);*/
		String accountFileName = "accountinitdata.json";
		ClassLoader classLoader = getClass().getClassLoader();
		File accountData = new File(classLoader.getResource(accountFileName).getFile());

		// read account data
		List<AccountForm> accountForms = new ObjectMapper().setVisibility(FIELD, ANY)
				.readValue(accountData, new TypeReference<List<AccountForm>>() {});
	    System.out.println(accountForms.size());

		accountForms.forEach(accountForm -> {
			registerOrUpdateService.register(accountForm);
		});
	}


}
