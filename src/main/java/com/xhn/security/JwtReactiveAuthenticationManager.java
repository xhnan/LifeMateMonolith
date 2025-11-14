package com.xhn.security;

import com.xhn.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * 响应式 JWT 认证管理器
 */
@Component
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtReactiveAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        return Mono.justOrEmpty(token)
                .flatMap(t -> {
                    try {
                        Claims claims = jwtUtil.parseToken(t).getBody();
                        String username = claims.getSubject();
//                        PrincipalInfo principalInfo = builder
//                                .roles(List.of("ROLE_USER")) // 可选：从 claims 解析实际角色
//                                .displayName(claims.get("name", String.class))
//                                .build();

                        PrincipalInfo principalInfo = PrincipalInfo.builder(0L, username)
                                .roles(List.of("ROLE_USER"))
                                .displayName(claims.get("name", String.class))
                                .build();

                        // 创建认证对象，这里简化处理，实际可以从 claims 中获取角色信息
                        Authentication auth = new UsernamePasswordAuthenticationToken(
                                principalInfo,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                        );

                        return Mono.just(auth);
                    } catch (Exception e) {
                        return Mono.empty();
                    }
                })
                .switchIfEmpty(Mono.empty());
    }
}

