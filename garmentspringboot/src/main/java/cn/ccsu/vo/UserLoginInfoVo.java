package cn.ccsu.vo;

import java.io.Serializable;

public class UserLoginInfoVo implements Serializable {
    private String token;

    public UserLoginInfoVo(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}