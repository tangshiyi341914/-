package com.project.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements UserDetails {
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  private String name;
  private String phone;
  private String address;
  @Getter(value = AccessLevel.NONE)
  private Boolean enabled;
  private String username;
  private String password;
  private String email;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
  private Date birthday;
  private String gender;
  @TableField(value = "cpnName")
  private String cpnName="";
  @TableField(exist = false)
  private String role;


  @Override
  public String getUsername() {
    return username;
  }


  @Override
  public String getPassword() {
    return password;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
