package com.ecom.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.BuyProductRequestDto;
import com.ecom.dto.BuyProductResponseDto;
import com.ecom.dto.FundRequestDto;
import com.ecom.dto.FundTransferResponseDto;
import com.ecom.dto.ProductDto;
import com.ecom.entity.Product;
import com.ecom.entity.User;
import com.ecom.exceptions.BalanceNotAvailableToBuyProduct;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.exceptions.ProductQtyNotAvailable;
import com.ecom.exceptions.UserNotFound;
import com.ecom.feignClient.BankClient;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;
import com.ecom.service.BuyProductService;

@Service
public class BuyProductServiceImpl implements BuyProductService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BankClient bankClient;

	@Override
	public BuyProductResponseDto buyProducts(BuyProductRequestDto requestDto) {
		
		BuyProductResponseDto response=new BuyProductResponseDto();
		Optional<User> user=userRepository.findById(requestDto.getUserId());
		if(!user.isPresent()) {
			throw new UserNotFound("User Not Found");
		}
		
		int totalCostOfProducts=0;
		List<ProductDto> products=requestDto.getProducts();
		for (ProductDto productDto : products) {
			Optional<Product> product=productRepository.findById(productDto.getProductId());
			if(!product.isPresent()) {
				throw new ProductNotFoundException("Product Not Found For Id="+productDto.getProductId());
			}
			if(!(product.get().getQty()>=productDto.getQty())) {
				throw new ProductQtyNotAvailable("Product Qty Not Available. Available Qty is ="+product.get().getQty());
			}
			totalCostOfProducts=totalCostOfProducts+(productDto.getQty()*product.get().getPrice());
		}
		
		boolean amountPresentInAccount=bankClient.checkAmount(requestDto.getAccountNumber(), totalCostOfProducts);
		if(!amountPresentInAccount) {
			throw new BalanceNotAvailableToBuyProduct("Account Have not Sufficient Balance to Buy Products.");
		}
		
		Function<String, Long> fun= Long::parseLong; 
		
		FundRequestDto requests=new FundRequestDto();
		requests.setFromAccount(requestDto.getAccountNumber());
		requests.setToAccount(fun.apply("55"));
		requests.setAmount(totalCostOfProducts);
		
		FundTransferResponseDto statusResponse=bankClient.fundTransfer(requests);
		if(statusResponse.getStatusCode()!=200) {
			response.setStatusMessage("Transaction Not Successful..");
			response.setStatusCode(800);
		}
		
		response.setStatusMessage("Products are Buyed Successfully..");
		response.setStatusCode(200);
		return response;
	}

}
