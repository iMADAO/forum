package com.project.web;

import java.util.List;

public class Page<T> {
	@Override
	public String toString() {
		return "Page [content=" + content + ", pageSize=" + pageSize
				+ ", pageNo=" + pageNo + ", totalPage=" + totalPage
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", isFirstPage=" + isFirstPage + ", isLastPage=" + isLastPage
				+ ", hasLastPage=" + hasLastPage + ", hasNextPage="
				+ hasNextPage + ", navigatepageNums=" + navigatepageNums + "]";
	}
	private List<T> content;
	private int pageSize;
	private int pageNo;
	private int totalPage;
	private int firstPage = 1;
	private int lastPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasLastPage;
	private boolean hasNextPage;
	private List<Integer> navigatepageNums;
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public boolean isHasLastPage() {
		return hasLastPage;
	}
	public void setHasLastPage(boolean hasLastPage) {
		this.hasLastPage = hasLastPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Integer> getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(List<Integer> navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}
	
	
}
