package com.project.dao.impl;

import com.project.bean.Admin;
import com.project.dao.AdminDAO;

public class AdminDAOImpl extends DAOImpl<Admin> implements AdminDAO {

	public Long getCountByAdminNamePassword(String adminName, String password) {
		String sql = "select count(*) from admin where admin_name=? and password=?";
		return getForValue(sql, adminName, password);
	}

	public Long getAdminNameCount(String adminName) {
		String sql = "select count(*) from admin where admin_name=?";
		return getForValue(sql, adminName);
	}

	public Admin getAdminByName(String adminName) {
		String sql = "select admin_id adminId, admin_name adminName from admin where admin_name = ?";
		return get(sql, adminName);
	}

}
