package cn.ccsu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo implements Serializable {
    private Integer goodsId;
    private String name;
    private Integer count;
    private String size;
    private String color;
    private String number;
    private Integer outCount;
}
