package com.android.news.searchmodel;

import java.util.List;

public class Response{
	private String kind;
	private Context context;
	private Queries queries;
	private SearchInformation searchInformation;
	private List<ItemsItem> items;
	private Url url;

	public void setKind(String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setContext(Context context){
		this.context = context;
	}

	public Context getContext(){
		return context;
	}

	public void setQueries(Queries queries){
		this.queries = queries;
	}

	public Queries getQueries(){
		return queries;
	}

	public void setSearchInformation(SearchInformation searchInformation){
		this.searchInformation = searchInformation;
	}

	public SearchInformation getSearchInformation(){
		return searchInformation;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public void setUrl(Url url){
		this.url = url;
	}

	public Url getUrl(){
		return url;
	}
}