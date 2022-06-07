package cn.ccsu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private Integer id;
    private String menuName;
    private Integer status;
    private String perms;
    private Integer isDelete;


}
