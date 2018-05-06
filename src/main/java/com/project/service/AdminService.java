package com.project.service;

import com.project.bean.Admin;
import com.project.dao.AdminDAO;
import com.project.dao.impl.AdminDAOImpl;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAOImpl();
	
	public boolean checkAdmin(String adminName, String password){
		Long count = adminDAO.getCountByAdminNamePassword(adminName, password);
		if(count<=0)
			return false;
		else
			return true;
	}
	
	public boolean checkIfAdminNameExists(String adminName){
		Long count = adminDAO.getAdminNameCount(adminName);
		if(count>0)
			return true;
		else 
			return false;
	}

	public Admin getAdminByName(String adminName) {
		return adminDAO.getAdminByName(adminName);
	}
}
