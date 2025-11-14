package com.xhn.security;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.security.Principal;
import java.time.Instant;
import java.util.*;

/**
 * 不可变的 Principal 信息，包含常见扩展字段。
 * 改进点：
 * - 支持可选 id（nullable）
 * - null-safe equals/hashCode
 * - 提供更便利的静态工厂/Builder 重载和 toBuilder()
 * - 增加常用帮助方法（hasRole/hasPermission）
 */
@Getter
public class PrincipalInfo implements Principal, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id; // 可为 null
    private final String username;
    private final String displayName;
    private final String email;
    private final List<String> roles;
    private final Set<String> permissions;
    private final String tenantId;
    private final Instant issuedAt;
    private final Instant expiresAt;
    private final Map<String, Object> attributes;
    private final boolean enabled;

    private PrincipalInfo(Builder b) {
        // id 可以为 null（某些场景下 token 里没有 id）
        this.id = b.id;
        this.username = Objects.requireNonNull(b.username, "username");
        this.displayName = b.displayName;
        this.email = b.email;
        this.roles = List.copyOf(b.roles == null ? List.of() : b.roles);
        this.permissions = Set.copyOf(b.permissions == null ? Set.of() : b.permissions);
        this.tenantId = b.tenantId;
        this.issuedAt = b.issuedAt;
        this.expiresAt = b.expiresAt;
        this.attributes = Map.copyOf(b.attributes == null ? Map.of() : b.attributes);
        this.enabled = b.enabled;
    }

    @Override
    public String getName() {
        return username;
    }

    /**
     * 是否包含给定角色（忽略大小写）
     */
    public boolean hasRole(String role) {
        if (role == null) return false;
        for (String r : roles) {
            if (role.equalsIgnoreCase(r)) return true;
        }
        return false;
    }

    /**
     * 是否包含给定权限（忽略大小写）
     */
    public boolean hasPermission(String permission) {
        if (permission == null) return false;
        for (String p : permissions) {
            if (permission.equalsIgnoreCase(p)) return true;
        }
        return false;
    }

    /**
     * 返回角色的不可变集合视图
     */
    public Set<String> getRolesSet() {
        return Set.copyOf(this.roles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrincipalInfo)) return false;
        PrincipalInfo that = (PrincipalInfo) o;
        return enabled == that.enabled &&
                Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(permissions, that.permissions) &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(issuedAt, that.issuedAt) &&
                Objects.equals(expiresAt, that.expiresAt) &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, displayName, email, roles, permissions, tenantId, issuedAt, expiresAt, attributes, enabled);
    }

    @Override
    public String toString() {
        return "PrincipalInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", permissions=" + permissions +
                ", tenantId='" + tenantId + '\'' +
                ", issuedAt=" + issuedAt +
                ", expiresAt=" + expiresAt +
                ", enabled=" + enabled +
                '}';
    }

    // --- 工厂与 Builder ---

    public static Builder builder(Long id, String username) {
        return new Builder(id, username);
    }

    public static Builder builder(String username) {
        return new Builder(null, username);
    }

    public static PrincipalInfo of(Long id, String username) {
        return builder(id, username).build();
    }

    public static PrincipalInfo of(String username) {
        return builder(username).build();
    }

    public Builder toBuilder() {
        Builder b = new Builder(this.id, this.username);
        b.displayName = this.displayName;
        b.email = this.email;
        b.roles = List.copyOf(this.roles);
        b.permissions = Set.copyOf(this.permissions);
        b.tenantId = this.tenantId;
        b.issuedAt = this.issuedAt;
        b.expiresAt = this.expiresAt;
        b.attributes = Map.copyOf(this.attributes);
        b.enabled = this.enabled;
        return b;
    }

    public static final class Builder {
        private final Long id;
        private final String username;
        private String displayName;
        private String email;
        private List<String> roles = List.of();
        private Set<String> permissions = Set.of();
        private String tenantId;
        private Instant issuedAt;
        private Instant expiresAt;
        private Map<String, Object> attributes = Map.of();
        private boolean enabled = true;

        public Builder(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder roles(Collection<String> roles) {
            this.roles = roles == null ? List.of() : List.copyOf(roles);
            return this;
        }

        public Builder permissions(Collection<String> permissions) {
            this.permissions = permissions == null ? Set.of() : Set.copyOf(permissions);
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder issuedAt(Instant issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public Builder expiresAt(Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder attributes(Map<String, Object> attributes) {
            this.attributes = attributes == null ? Map.of() : Map.copyOf(attributes);
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public PrincipalInfo build() {
            return new PrincipalInfo(this);
        }
    }
}

