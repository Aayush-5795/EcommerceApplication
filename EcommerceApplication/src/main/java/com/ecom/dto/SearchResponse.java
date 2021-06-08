package com.ecom.dto;

import java.util.List;

public class SearchResponse {
	
	List<SearchProductResponseDto> list;
	private String statusMsg;
    private int statusCode;
    
	public List<SearchProductResponseDto> getList() {
		return list;
	}
	public void setList(List<SearchProductResponseDto> list) {
		this.list = list;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
    
    

}
