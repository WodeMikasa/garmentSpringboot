package cn.ccsu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private Integer id;
    private String name;
    private Integer count;
    private String size;
    private String color;
    private String number;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
