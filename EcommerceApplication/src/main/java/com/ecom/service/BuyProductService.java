package com.ecom.service;

import com.ecom.dto.BuyProductRequestDto;
import com.ecom.dto.BuyProductResponseDto;

public interface BuyProductService {

	BuyProductResponseDto buyProducts(BuyProductRequestDto requestDto);

}
