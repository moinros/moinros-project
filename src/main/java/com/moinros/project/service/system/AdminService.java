package com.moinros.project.service.system;

import com.moinros.project.model.pojo.system.Admin;
import com.moinros.project.model.pojo.system.AdminLog;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 12:48
 * @Verison 1.0
 */
public interface AdminService {

    Admin login(String username, String password, AdminLog log);

    Admin qureyAdminById(Integer id);

}
