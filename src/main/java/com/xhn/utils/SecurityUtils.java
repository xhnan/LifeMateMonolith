package com.xhn.utils;

import com.xhn.security.PrincipalInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    
    /**
     * 获取当前登录用户信息
     */
    public static PrincipalInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof PrincipalInfo) {
            return (PrincipalInfo) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前用户名
     */
    public static Long getCurrentUserId() {
        PrincipalInfo user = getCurrentUser();
        return user != null ? user.getId() : null;
    }

    
    /**
     * 获取当前用户名
     */
    public static String getCurrentUsername() {
        PrincipalInfo user = getCurrentUser();
        return user != null ? user.getUsername() : null;
    }
}
