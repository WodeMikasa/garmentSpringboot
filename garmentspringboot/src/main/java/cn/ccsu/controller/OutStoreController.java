package cn.ccsu.controller;

import cn.ccsu.entity.OutStore;
import cn.ccsu.service.OutStoreService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/queryAll")
    public ResponseResult queryAll(Integer pageNum,Integer pageSize,@RequestParam(defaultValue = "") String number,@RequestParam(defaultValue = "") String storage){
        return outStoreService.queryAll(pageNum,pageSize,number,storage);
    }

    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id){
        return outStoreService.delete(id);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody OutStore outStore){
        return outStoreService.update(outStore);
    }

    @GetMapping("/get/{id}")
    public ResponseResult get(@PathVariable Integer id){
        return outStoreService.getById(id);
    }

}
