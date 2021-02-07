package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.AdminService;
import com.yochalyc.myblog.blog.dal.dao.AdminDAO;
import com.yochalyc.myblog.blog.dal.model.AdminDO;
import com.yochalyc.myblog.blog.exception.AdminStatusErrorException;
import com.yochalyc.myblog.blog.exception.PwdValidationException;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public AdminDO login(String adminName, String password) throws NullPointerException {
        AdminDO admin = adminDAO.findFirstByAdminName(adminName);
        if (admin == null) {
            throw new NullPointerException();
        }
        if (!admin.getIsHealth()) {
            throw new AdminStatusErrorException();
        }
        if (!isPasswordValid(admin.getPassword(), admin.getSalt(), password)) {
            throw new PwdValidationException();
        }



        return null;
    }

    private Boolean isPasswordValid(String pwdHash, String salt, String inputPwd) {
        String hashedPwd = Md5Util.encryptWithSalt(inputPwd, salt);
        return StringUtils.equals(pwdHash, hashedPwd);
    }

}
