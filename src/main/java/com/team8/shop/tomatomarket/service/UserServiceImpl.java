package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.UserMyProfileDto;
import com.team8.shop.tomatomarket.dto.UserRequestDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto update(UserMyProfileDto userMyProfileDto){
        String username = userMyProfileDto.getUsername();
        String nickname = userMyProfileDto.getNickName();

        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자가 없습니다"));

        if(user.isRole() || user.isValidUsername(user.getUsername()))
        {
            user.updateNickName(nickname);
            return new UserResponseDto(user);
        }
        throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
    }

    @Transactional
    @Override
    public UserResponseDto getProfile(User user){
        return new UserResponseDto(user);
    }

}
