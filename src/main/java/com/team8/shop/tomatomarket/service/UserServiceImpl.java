package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.LoginReqDto;
import com.team8.shop.tomatomarket.dto.LoginRespDto;
import com.team8.shop.tomatomarket.dto.SignupReqDto;
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
}
