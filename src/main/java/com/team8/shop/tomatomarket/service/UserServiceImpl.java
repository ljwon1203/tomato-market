package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.UserMyProfileDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    //(고객) 프로필 설정
    @Transactional
    @Override
    public UserResponseDto update(UserMyProfileDto userMyProfileDto){
        Long userId = userMyProfileDto.getId();
        String nickname = userMyProfileDto.getNickname();

        User user = userRepository.findById(userId).orElseThrow(
                ()->new IllegalArgumentException("사용자가 존재하지 않습니다."));

        if(userId == user.getId())
        {
            user.updateNickName(nickname);
            userRepository.save(user);
            return new UserResponseDto(user);
        }
        throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
    }

    //(고객) 프로필 조회
    @Override
    public UserResponseDto getProfile(User user){
        return new UserResponseDto(user);
    }

}
