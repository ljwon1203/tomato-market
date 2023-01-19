package com.team8.shop.tomatomarket.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class PageableServiceReqDto {
    private final int page;
    private final int size;
    private final String sortBy;
    private final boolean isAsc;

    public PageableServiceReqDto(int page, int size){
        this.page = page;
        this.size = size;
        this.sortBy = "Id";
        this.isAsc = true;
    }
}
