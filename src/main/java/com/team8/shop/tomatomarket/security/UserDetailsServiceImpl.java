package com.team8.shop.tomatomarket.security;

import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // 유저가 ID와 패스워드 입력시 메소드 실행.
    // username의 정보를 db에서 검색, 없을경우 에러발생, 찾았을 경우 getAuthorities메소드 실행
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));

        return new UserDetailsImpl(user);
    }

}
