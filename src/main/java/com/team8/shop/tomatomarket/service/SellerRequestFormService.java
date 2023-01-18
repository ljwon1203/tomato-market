package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;

import java.util.List;

public interface SellerRequestFormService {
    void createDisapprovedForm(CreateDisapprovedSellerFormReqDto dto);

    List<GetSellerWaitingsRespDto> getSellerWaitings();

    void approveSellerAuth(Long waitingId);
}
