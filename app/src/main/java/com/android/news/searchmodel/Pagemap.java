package com.android.news.searchmodel;

import java.util.List;

public class Pagemap{
	private List<CseThumbnailItem> cseThumbnail;
	private List<MetatagsItem> metatags;
	private List<CseImageItem> cseImage;

	public void setCseThumbnail(List<CseThumbnailItem> cseThumbnail){
		this.cseThumbnail = cseThumbnail;
	}

	public List<CseThumbnailItem> getCseThumbnail(){
		return cseThumbnail;
	}

	public void setMetatags(List<MetatagsItem> metatags){
		this.metatags = metatags;
	}

	public List<MetatagsItem> getMetatags(){
		return metatags;
	}

	public void setCseImage(List<CseImageItem> cseImage){
		this.cseImage = cseImage;
	}

	public List<CseImageItem> getCseImage(){
		return cseImage;
	}
}