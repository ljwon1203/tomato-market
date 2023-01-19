package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.LoginReqDto;
import com.team8.shop.tomatomarket.dto.LoginRespDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import com.team8.shop.tomatomarket.util.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public LoginRespDto login(LoginReqDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        // 유저 검증
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        // 패스워드 검증
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        // 유저 정보로 부터 토큰 추출
        String jwtToken = jwtUtils.createToken(user.getUsername(), user.getRole());

        // dto에 넣어서 반환
        return new LoginRespDto(jwtToken);
    }
}
