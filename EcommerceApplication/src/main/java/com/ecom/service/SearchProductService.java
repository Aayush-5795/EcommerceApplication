package com.ecom.service;

import java.util.List;

import com.ecom.dto.SearchProductRequestDto;
import com.ecom.dto.SearchProductResponseDto;
import com.ecom.dto.SearchResponse;

public interface SearchProductService {

	SearchResponse getProduct(SearchProductRequestDto searchProductRequestDto);

}
