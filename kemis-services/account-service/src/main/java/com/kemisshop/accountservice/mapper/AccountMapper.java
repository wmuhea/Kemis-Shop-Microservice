package com.kemisshop.accountservice.mapper;

import com.kemisshop.accountservice.model.Account;

import com.kemisshop.accountservice.model.BuyerAccount;
import com.kemisshop.accountservice.model.SellerAccount;
import com.kemisshop.accountservice.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Mapper(componentModel = "spring", uses = {UserProfileMapper.class})
public interface AccountMapper {

    Account toAccount(AccountRequestDto accountRequestDto);


    BuyerAccountResponseDto toBuyerDto(BuyerAccount buyerAccount);


    SellerAccountResponseDto toSellerDto(SellerAccount buyerAccount);

    @ObjectFactory
    default Account createAccount(AccountRequestDto accountDto) {
       return AccountFactory.getAccount(accountDto.getType());
    }

    default ResponseBean toResponseBean(HttpStatus status, Object responsePayLoad) {
        return new ResponseBean(status.name(), responsePayLoad);
    }

    default ResponseBean toResponseBean(HttpStatus status, Page<?> responsePayLoad) {
        return new ResponseBean(status.name(), responsePayLoad.getTotalPages(), responsePayLoad);
    }

    default AccountResponseDto toDto(Account account) {
        AccountResponseDto responseDto = null;
        if (account instanceof BuyerAccount)
            responseDto = this.toBuyerDto((BuyerAccount) account);
        if(account instanceof SellerAccount)
            responseDto = this.toSellerDto((SellerAccount) account);
        return responseDto;
    }


}
