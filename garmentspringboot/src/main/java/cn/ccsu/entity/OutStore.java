package cn.ccsu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutStore implements Serializable {
    private Integer id;
    private String number;
    private String storage;
    private String agent;
    private String towhere;
    private Date outTime;
    private List<OutStoreDetail> detail;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
