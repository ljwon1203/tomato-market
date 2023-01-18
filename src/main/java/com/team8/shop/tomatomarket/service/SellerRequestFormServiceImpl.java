package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.CreateDisapprovedSellerFormReqDto;
import com.team8.shop.tomatomarket.entity.*;
import com.team8.shop.tomatomarket.repository.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerRequestFormServiceImpl implements SellerRequestFormService{

    private final UserRepository userRepository;
    private final SellerRequestFormRepository sellerRequestFormRepository;

    @Override
    public void createDisapprovedForm(CreateDisapprovedSellerFormReqDto dto) {
        SellerRequestForm instance;
        String username = dto.getUsername();
        String introduce = dto.getIntroduce();

        User user = userRepository.findByUsername(username).get();

        Optional<SellerRequestForm> sellerRequestForm = sellerRequestFormRepository.findByUserId(user.getId());

        // 만약 기존 데이터가 있다면, isApproval를 false 로 무조건 업데이트한다.
        if(sellerRequestForm.isPresent()){
            instance = sellerRequestForm.get();
            instance.disapprove();
        }
        // 기존 데이터가 없다면, 새로운 인스턴스를 만들어서 저장한다. (isApproval의 기본값은 false 이기 때문에, 굳이 업데이트 할 필요가 없다.)
        else{
            instance = new SellerRequestForm(user, introduce);
        }
        sellerRequestFormRepository.save(instance);
    }
}
