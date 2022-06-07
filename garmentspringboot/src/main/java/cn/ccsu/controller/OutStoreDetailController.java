package cn.ccsu.controller;

import cn.ccsu.service.OutStoreDetailService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class OutStoreDetailController {
    @Autowired
    private OutStoreDetailService outStoreDetailService;

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAnyAuthority('superadmin:power','admin:power')")
    public ResponseResult query(@PathVariable Integer id){
        return outStoreDetailService.query(id);
    }
}
