package com.kemisshop.accountservice.mapper;

import com.kemisshop.accountservice.dto.UserProfileDto;
import com.kemisshop.accountservice.app.model.UserProfile;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toProfile(UserProfileDto userProfileDto);

    UserProfileDto toDto(UserProfile userProfile);


}

