package cn.ccsu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutStoreDetail {
    private Integer id;
    private String name;
    private Integer count;
    private String size;
    private String color;
    private Integer goodsId;
    private Date createTime;
    private Date updateTime;
}
