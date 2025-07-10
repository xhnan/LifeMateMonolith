package com.xhn.filter;


import com.xhn.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;

/**
 * @author xhn
 * @date 2025/7/8 10:10
 * @description
 */
public class JwtAuthenticationFilter implements ServerSecurityContextRepository {

    private final JwtUtil jwtUtil;

    // 通过构造函数注入JwtUtil
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                // 验证JWT并提取信息
                Claims claims = jwtUtil.parseToken(token);
                String userIdStr = claims.getSubject();
                //验证token是否有效
                // 1. 检查过期时间
                Date expiration = claims.getExpiration();
                if (expiration.before(new Date())) {
                    return Mono.empty(); // token已过期
                }

                // 创建认证对象
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userIdStr, null,
                                Collections.singletonList(new SimpleGrantedAuthority("USER")));

                // 创建安全上下文并设置认证信息
                SecurityContext context = new SecurityContextImpl(auth);
                return Mono.just(context);
            } catch (Exception e) {
                return Mono.empty();
            }
        }
        return Mono.empty();
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }
}