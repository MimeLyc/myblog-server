package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.AdminService;
import com.yochalyc.myblog.blog.dal.dao.AdminDAO;
import com.yochalyc.myblog.blog.dal.model.AdminDO;
import com.yochalyc.myblog.blog.exception.AdminStatusErrorException;
import com.yochalyc.myblog.blog.exception.PwdValidationException;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

import static com.yochalyc.myblog.blog.config.GlobalProperties.ADMIN_TOKEN_DURATION;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public AdminDO login(String adminName, String password) throws NullPointerException {
        AdminDO admin = adminDAO.findByAdminName(adminName);
        if (admin == null) {
            throw new NullPointerException();
        }
        if (!admin.getIsHealth()) {
            throw new AdminStatusErrorException();
        }
        if (!isPasswordValid(admin.getPassword(), admin.getSalt(), password)) {
            throw new PwdValidationException();
        }

        Date now = new Date();

        admin.setLastLoginTime(now);
        admin.setTokenExpireIn(DateUtils.addDays(now, ADMIN_TOKEN_DURATION));
        admin.setAccessToken(Md5Util.randomToken_16());

        adminDAO.save(admin);

        return admin;
    }

    @Override
    public void register(String adminName, String password) {
        AdminDO admin = adminDAO.findByAdminName(adminName);
        Assert.isNull(admin, "用户已经存在");

        AdminDO adminDO = new AdminDO(adminName, password);
        adminDO.setAdminId(Md5Util.randomToken_16());
        adminDO.setIsHealth(true);

        String salt = Md5Util.randomSalt(16);
        adminDO.setSalt(salt);
        adminDO.setPassword(Md5Util.encryptWithSalt(password, salt));

        Date now = new Date();
        adminDO.setLastLoginTime(now);
        adminDO.setAccessToken(Md5Util.randomToken_16());
        adminDO.setTokenExpireIn(DateUtils.addDays(now, ADMIN_TOKEN_DURATION));

        adminDAO.save(adminDO);
    }


    private Boolean isPasswordValid(String pwdHash, String salt, String inputPwd) {
        String hashedPwd = Md5Util.encryptWithSalt(inputPwd, salt);
        return StringUtils.equals(pwdHash, hashedPwd);
    }

}
