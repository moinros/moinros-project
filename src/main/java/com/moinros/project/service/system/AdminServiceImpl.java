package com.moinros.project.service.system;

import com.moinros.project.model.dao.system.AdminLogMapper;
import com.moinros.project.model.dao.system.AdminMapper;
import com.moinros.project.model.pojo.system.Admin;
import com.moinros.project.model.pojo.system.AdminLog;
import com.moinros.project.tool.cipher.Token;
import com.moinros.project.tool.util.date.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 12:50
 * @Verison 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminLogMapper logMapper;

    @Override
    public Admin login(String username, String password, AdminLog log) {
        if (username != null && password != null) {
            Admin a = new Admin();
            a.setAdminName(username);
            Admin admin = adminMapper.selectAdmin(a);
            if (admin != null) {
                Token token = new Token();
                boolean f = token.validate(password, admin.getPassword());
                if (f) {
                    log.setAdminId(admin.getAid());
                    log.setLoginTime(DateFormatUtil.getDateTime());
                    Integer num = logMapper.insertAdminLog(log);
                    return (num != null && num.intValue() > 0) ? admin : null;
                }
            }
        }
        return null;
    }

    @Override
    public Admin qureyAdminById(Integer id) {
        return null;
    }
}
