package com.yochalyc.myblog.blog.dal.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nick;

    private String email;

    private String account;

    private String password;

    public UserDO(String nick, String email, String account, String password) {
        this.nick = nick;
        this.email = email;
        this.account = account;
        this.password = password;
    }
}
