package com.project.dao;

import com.project.bean.Admin;

public interface AdminDAO {
	Long getCountByAdminNamePassword(String adminName, String password);

	Long getAdminNameCount(String adminName);

	Admin getAdminByName(String adminName);
}
