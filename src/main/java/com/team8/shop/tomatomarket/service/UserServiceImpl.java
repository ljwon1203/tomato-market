package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.UserRequestDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto update(Long userId, UserRequestDto userRequestDto, User user){

        if(user.isRole() || user.isValidId(user.getId()))
        {
            user.updateNickName(userRequestDto.getNickname());
            return new UserResponseDto(user);
        }
        throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        //
    }

    @Transactional
    @Override
    public UserResponseDto getProfile(User user){
        return new UserResponseDto(user);
    }

}
