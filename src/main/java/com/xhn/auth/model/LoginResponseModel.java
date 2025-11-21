package com.xhn.auth.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xhn
 * @date 2025/11/14 14:16
 * @description
 */
@Data
public class LoginResponseModel implements Serializable {
    private String token;
    private String username;
    private String refreshToken;
    private String nickname;
    private List<String> roles;
    private List<String> permissions;
    private Date expire;
    private String avatar;
}
