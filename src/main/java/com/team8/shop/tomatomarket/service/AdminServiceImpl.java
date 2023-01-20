package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.PageableServiceReqDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

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
