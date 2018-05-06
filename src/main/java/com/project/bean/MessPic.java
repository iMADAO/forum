package com.project.bean;

public class MessPic {
	private String picId;
	private String picPath;
	private String messId;
	private int picOrder;
	public int getPicOrder() {
		return picOrder;
	}
	public void setPicOrder(int picOrder) {
		this.picOrder = picOrder;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getMessId() {
		return messId;
	}
	public void setMessId(String messId) {
		this.messId = messId;
	}
	@Override
	public String toString() {
		return "MessPic [picId=" + picId + ", picPath=" + picPath + ", messId="
				+ messId + ", picOrder=" + picOrder + "]";
	}
	
	
}
