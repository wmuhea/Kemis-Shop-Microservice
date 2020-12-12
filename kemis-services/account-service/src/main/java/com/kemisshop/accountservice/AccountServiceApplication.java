package com.kemisshop.accountservice;

import com.kemisshop.accountservice.dto.AccountRequestDto;
import com.kemisshop.accountservice.dto.UserProfileDto;
import com.kemisshop.accountservice.model.Account;
import com.kemisshop.accountservice.model.AccountType;
import com.kemisshop.accountservice.model.Type;
import com.kemisshop.accountservice.service.AccountService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemisshop.accountservice.mapper.AccountMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import java.io.File;
import java.util.LinkedList;
import java.util.List;


import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
public class AccountServiceApplication implements CommandLineRunner {

	private final AccountService<Account> accountService;

	private final AccountMapper accountMapper;

	private int count;

	public AccountServiceApplication(AccountService<Account> accountService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.accountMapper = accountMapper;
		this.count = 0;
	}



	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*populateRoleTable();
		populateUserTable();*/

	}

	private void populateRoleTable() {
		accountService.save(new AccountType(Type.findByLabel("Seller")));
		accountService.save(new AccountType(Type.findByLabel("Buyer")));
		accountService.save(new AccountType(Type.findByLabel("Admin")));
	}

	private void populateUserTable()  throws Exception{
		/*createUsers(importedFile);*/
		String accountFileName = "InitialData0.json";
		String userFileName = "userprofiledata.json";
		ClassLoader classLoader = getClass().getClassLoader();

		File accountData = new File(classLoader.getResource(accountFileName).getFile());
		File userProfileData = new File(classLoader.getResource(userFileName).getFile());

		// read account data
		List<AccountRequestDto> accountRequestDtos = new ObjectMapper().setVisibility(FIELD, ANY)
				.readValue(accountData, new TypeReference<List<AccountRequestDto>>() {});
	System.out.println(accountRequestDtos.size());
		// read user data
		LinkedList<UserProfileDto> userProfileDtos = new ObjectMapper().setVisibility(FIELD, ANY)
				.readValue(userProfileData, new TypeReference<LinkedList<UserProfileDto>>() {});

	System.out.println(userProfileDtos.size());
		accountRequestDtos.forEach(accountRequestDto -> {
			// set user profile dto to account dto first
			accountRequestDto.setUserProfile(userProfileDtos.removeFirst());
			Account account = accountMapper.toAccount(accountRequestDto);
			AccountType accountType = accountService.getAccountType(Type.findByLabel(accountRequestDto.getType()));
			account.setAccountType(accountType);

			accountService.save(account);
		});
	}


}
