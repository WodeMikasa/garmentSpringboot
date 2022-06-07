package cn.ccsu.controller;

import cn.ccsu.entity.OutStore;
import cn.ccsu.service.OutStoreService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/out")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;

    @PostMapping("/store")
    @PreAuthorize("hasAnyAuthority('superadmin:power','admin:power')")
    public ResponseResult outStore(@RequestBody OutStore outStore){
        return outStoreService.outStore(outStore);

    }


}
