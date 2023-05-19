package com.security.study.dto.user;

import com.security.study.entity.user.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto implements UserDetails {

    private String nickname;
    private String phone;
    private String crn;
    private Timestamp createdAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 학습 진행 후 코딩
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    public static UserDto fromEntity(UserEntity user) {
        UserDto dto = new UserDto();

        dto.nickname = user.getNickname();
        dto.phone = user.getPhone();
        dto.crn = user.getCrn();
        dto.createdAt= user.getCreatedAt();

        return dto;
    }
}
