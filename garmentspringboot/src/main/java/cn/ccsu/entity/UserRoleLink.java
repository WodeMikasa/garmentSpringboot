package cn.ccsu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleLink implements Serializable {
    private Integer roleId;
    private Integer userId;
}
