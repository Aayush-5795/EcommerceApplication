package com.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.controller.SearchProductController;
import com.ecom.dto.CategoryDto;
import com.ecom.dto.SearchProductRequestDto;
import com.ecom.dto.SearchProductResponseDto;
import com.ecom.dto.SearchResponse;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.service.SearchProductService;
/**
 * 
 * @author aayush.pandit
 *
 */
@Service
public class SearchProductServiceImpl implements SearchProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SearchProductController.class);

	
	@Override
	public SearchResponse getProduct(SearchProductRequestDto searchProductRequestDto) {
		logger.info("inside getProduct() serviceimpl method");
		SearchResponse response=new SearchResponse();
		String productName=searchProductRequestDto.getProductName();
		String categoryName=searchProductRequestDto.getCategoryName();
		List<SearchProductResponseDto> responseDto=new ArrayList<>();
		List<Product> productlist=new ArrayList<>();
		List<Category> categorylist=new ArrayList<>();
		
		if(productName!=null && categoryName==null) {
			productlist=productRepository.findByNameContaining(productName);
			productlist.forEach(product->{
				SearchProductResponseDto search=new SearchProductResponseDto();
				CategoryDto catdto=new CategoryDto();
				BeanUtils.copyProperties(product, search);
				BeanUtils.copyProperties(product.getCategory(), catdto);
				search.setCategory(catdto);
				responseDto.add(search);
			});
			
			/*
			 * for (Product product : productlist) { SearchProductResponseDto search=new
			 * SearchProductResponseDto(); CategoryDto catdto=new CategoryDto();
			 * BeanUtils.copyProperties(product, search);
			 * BeanUtils.copyProperties(product.getCategory(), catdto);
			 * search.setCategory(catdto); responseDto.add(search); }
			 */
		}
		
		
		
		if(categoryName!=null && productName==null) {
			categorylist=categoryRepository.findByNameContaining(categoryName);
			for (Category category : categorylist) {
				productlist=productRepository.findByCategory(category);
				for (Product product : productlist) {
					SearchProductResponseDto search=new SearchProductResponseDto();
					CategoryDto catdto=new CategoryDto();
					BeanUtils.copyProperties(product, search);
					BeanUtils.copyProperties(product.getCategory(), catdto);
					search.setCategory(catdto);
					responseDto.add(search);
				}
			}
		}
		
		if(categoryName!=null && productName!=null) {
			categorylist=categoryRepository.findByNameContaining(categoryName);
			for (Category category : categorylist) {
				productlist=productRepository.findByNameContainingAndCategory(productName, category);
				for (Product product : productlist) {
					SearchProductResponseDto search=new SearchProductResponseDto();
					CategoryDto catdto=new CategoryDto();
					BeanUtils.copyProperties(product, search);
					BeanUtils.copyProperties(product.getCategory(), catdto);
					search.setCategory(catdto);
					responseDto.add(search);
				}
			}
			
		}
		
		if(categoryName==null && productName==null || responseDto.size()==0) {
			logger.info("Product Not Found..");
			throw new ProductNotFoundException("Product Not Found..");
		}
		
		response.setList(responseDto);
		response.setStatusMsg("Product search Successfully");
		response.setStatusCode(800);
		
		logger.info("exiting getProduct() serviceimpl method");
		return response;
	}

}
