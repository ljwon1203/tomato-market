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
    private final SellerRepository sellerRepository;
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
    
    @Override
    public List<GetSellerWaitingsRespDto> getSellerWaitings() {
        return sellerRequestFormRepository.findAll()
                                            .stream()
                                            .map(GetSellerWaitingsRespDto::new)
                                            .collect(Collectors.toList());
    }

    @Override
    public void approveSellerAuth(Long waitingId) {
        // id에 해당되는 요청폼을 불러옵니다.
        SellerRequestForm waiting = sellerRequestFormRepository.findById(waitingId).orElseThrow(
                () -> new IllegalArgumentException("요청이 존재하지 않습니다.")
        );

        // 요청폼으로부터 유저정보를 불러와서, role을 변경해줍니다.
        User user = waiting.getUser();
        user.setRoleSeller();

        // role을 변경한 user와 저장되어있던 introduce를 이용해 새로운 seller 객체를 만듭니다.
        Seller seller = new Seller(user, waiting.getIntroduce());

        // 모든 작업을 마친 뒤, 요청폼의 승인여부를 승인으로 변경해줍니다.
        waiting.approve();

        // 변경된 모든 entity를 repository에 저장합니다.
        userRepository.save(user);
        sellerRepository.save(seller);
        sellerRequestFormRepository.save(waiting);
    }
}
