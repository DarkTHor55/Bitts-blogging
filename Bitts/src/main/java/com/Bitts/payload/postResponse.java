package com.Bitts.payload;

import java.util.List;

import com.Bitts.model.newPost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class postResponse {
	
	private List<newPost> content;
	private int pageNumber;
	private int pageSize;
	private int totelElement;
	private int totlePage;
	private boolean lastPage;
	
	public postResponse(List<newPost> content, int pageNumber, int pageSize, int totelElement, int totlePage,
			boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totelElement = totelElement;
		this.totlePage = totlePage;
		this.lastPage = lastPage;
	}
	public List<newPost> getContent() {
		return content;
	}
	public void setContent(List<newPost> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotelElement() {
		return totelElement;
	}
	public void setTotelElement(int totelElement) {
		this.totelElement = totelElement;
	}
	public int getTotlePage() {
		return totlePage;
	}
	public void setTotlePage(int totlePage) {
		this.totlePage = totlePage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public postResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
