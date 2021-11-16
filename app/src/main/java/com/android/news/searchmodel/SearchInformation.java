package com.android.news.searchmodel;

public class SearchInformation{
	private double searchTime;
	private String totalResults;
	private String formattedTotalResults;
	private String formattedSearchTime;

	public void setSearchTime(double searchTime){
		this.searchTime = searchTime;
	}

	public double getSearchTime(){
		return searchTime;
	}

	public void setTotalResults(String totalResults){
		this.totalResults = totalResults;
	}

	public String getTotalResults(){
		return totalResults;
	}

	public void setFormattedTotalResults(String formattedTotalResults){
		this.formattedTotalResults = formattedTotalResults;
	}

	public String getFormattedTotalResults(){
		return formattedTotalResults;
	}

	public void setFormattedSearchTime(String formattedSearchTime){
		this.formattedSearchTime = formattedSearchTime;
	}

	public String getFormattedSearchTime(){
		return formattedSearchTime;
	}
}
