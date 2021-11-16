package com.android.news.searchmodel;

public class RequestItem{
	private String inputEncoding;
	private String totalResults;
	private int startIndex;
	private String outputEncoding;
	private String searchTerms;
	private String cx;
	private int count;
	private String safe;
	private String title;

	public void setInputEncoding(String inputEncoding){
		this.inputEncoding = inputEncoding;
	}

	public String getInputEncoding(){
		return inputEncoding;
	}

	public void setTotalResults(String totalResults){
		this.totalResults = totalResults;
	}

	public String getTotalResults(){
		return totalResults;
	}

	public void setStartIndex(int startIndex){
		this.startIndex = startIndex;
	}

	public int getStartIndex(){
		return startIndex;
	}

	public void setOutputEncoding(String outputEncoding){
		this.outputEncoding = outputEncoding;
	}

	public String getOutputEncoding(){
		return outputEncoding;
	}

	public void setSearchTerms(String searchTerms){
		this.searchTerms = searchTerms;
	}

	public String getSearchTerms(){
		return searchTerms;
	}

	public void setCx(String cx){
		this.cx = cx;
	}

	public String getCx(){
		return cx;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setSafe(String safe){
		this.safe = safe;
	}

	public String getSafe(){
		return safe;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}
