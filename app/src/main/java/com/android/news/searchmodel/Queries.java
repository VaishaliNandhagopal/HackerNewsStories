package com.android.news.searchmodel;

import java.util.List;

public class Queries{
	private List<RequestItem> request;
	private List<NextPageItem> nextPage;

	public void setRequest(List<RequestItem> request){
		this.request = request;
	}

	public List<RequestItem> getRequest(){
		return request;
	}

	public void setNextPage(List<NextPageItem> nextPage){
		this.nextPage = nextPage;
	}

	public List<NextPageItem> getNextPage(){
		return nextPage;
	}
}