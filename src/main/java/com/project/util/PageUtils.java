package com.project.util;

import java.util.ArrayList;
import java.util.List;

import com.project.web.Page;

public class PageUtils<T> {
	public Page<T> getPage(List<T> content, Long total, Integer pageSize, Integer pageNo){
		Page<T> page = new Page<>();
		page.setContent(content);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		if(pageNo==1){
			page.setFirstPage(true);
		}
		int totalPage = (int) (total / pageSize);
		if(total % pageSize !=0){
			totalPage++;
		}
		page.setTotalPage(totalPage);
		page.setLastPage(totalPage);
		if(pageNo>=totalPage){
			page.setLastPage(true);
		}
		
		if(page.isFirstPage()){
			page.setHasLastPage(false);
		}else{
			page.setHasLastPage(true);
		}
		
		if(page.isLastPage()){
			page.setHasNextPage(false);
		}else{
			page.setHasNextPage(true);
		}
		
		List<Integer> navigatepageNums = new ArrayList<>();
		if(pageNo-2>=1){
			navigatepageNums.add(pageNo-2);
		}
		if(pageNo-1>=1){
			navigatepageNums.add(pageNo-1);
		}
		navigatepageNums.add(pageNo);
		
		if(pageNo+1<=totalPage){
			navigatepageNums.add(pageNo+1);
		}
		if(pageNo+2<=totalPage){
			navigatepageNums.add(pageNo+2);
		}
		page.setNavigatepageNums(navigatepageNums);
		return page;
	}
}
