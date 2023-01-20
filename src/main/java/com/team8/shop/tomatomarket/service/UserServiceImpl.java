package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import com.team8.shop.tomatomarket.util.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginRespDto login(LoginReqDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        // 유저 검증
        User user = _getUser(username);

        // 패스워드 검증
        if(!user.isValidPassword(password, passwordEncoder)){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        // 유저 정보로 부터 토큰 추출
        String jwtToken = jwtUtils.createToken(user.getUsername(), user.getRole());

        // dto에 넣어서 반환
        return new LoginRespDto(jwtToken);
    }

    @Override
    public void signup(SignupReqDto dto) {
        String username = dto.getUsername();
        String nickname = dto.getNickname();
        String password = dto.getPassword();

        // username 중복검증
        boolean isExistUsername = userRepository.existsByUsername(username);
        if(isExistUsername){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }

        // nickname 중복검증
        boolean isExistNickname = userRepository.existsByNickname(nickname);
        if(isExistNickname){
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }

        // 패스워드 암호화
        String encodePassword = passwordEncoder.encode(password);

        // 새로운 유저 생성
        User user = new User(username,nickname,encodePassword);

        // 저장
        userRepository.save(user);
    }

    //(고객) 프로필 설정
    @Override
    @Transactional
    public UserResponseDto update(UserMyProfileDto userMyProfileDto){
        Long userId = userMyProfileDto.getId();
        String nickname = userMyProfileDto.getNickname();

        User user = _getUser(userId);

        user.updateNickName(nickname);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    //(고객) 프로필 조회
    @Override
    public UserResponseDto getProfile(Long userId){
        User user = _getUser(userId);
        return new UserResponseDto(user);
    }

    // 내부 사용 : 유저 검증 by id
    private User _getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(
                ()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }

    // 내부 사용 : 유저 검증 by username
    private User _getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(
                ()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }

    @Override
    public List<UserResponseDto> getUserList(PageableServiceReqDto dto) {
        int page = dto.getPage();
        int size = dto.getSize();
        String sortBy = dto.getSortBy();
        boolean isAsc = dto.isAsc();

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        List<User> userList = userRepository.findAll(pageable).toList();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : userList) {
            userResponseDtos.add(new UserResponseDto(user));
        }
        return userResponseDtos;
    }
}
