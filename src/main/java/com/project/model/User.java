package com.project.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
  private Boolean enabled;
  private String username;
  private String password;
  private String email;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
  private Date birthday;
  private String gender;
  @TableField(exist = false)
  private Integer   page;   //当前页码数
  @TableField(exist = false)
  private Integer   rows;   //每页显示的件数
  @TableField(exist = false)
  private Integer[] ids;   //用于批量删除
  @TableField(exist = false)
  private Role role;
  @Override
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
