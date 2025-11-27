package com.xhn.auth.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Set<String> permissions;
    private Date expires;
    private String avatar;
}
