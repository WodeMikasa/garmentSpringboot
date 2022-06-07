package cn.ccsu.service.impl;

import cn.ccsu.entity.OutStoreDetail;
import cn.ccsu.mapper.OutStoreDetailMapper;
import cn.ccsu.mapper.OutStoreLinkMapper;
import cn.ccsu.service.OutStoreDetailService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutStoreDetailServiceImpl implements OutStoreDetailService {
    @Autowired
    private OutStoreDetailMapper outStoreDetailMapper;
    @Autowired
    private OutStoreLinkMapper outStoreLinkMapper;
    @Override
    public ResponseResult query(Integer id) {
        List<Integer> detailIds= outStoreLinkMapper.selectById(id);
        List<OutStoreDetail> outStoreDetails=new ArrayList<>();
        for (Integer detailId:detailIds){
             outStoreDetails.add(outStoreDetailMapper.selectById(detailId));
        }


        return ResponseResult.okResult(outStoreDetails);
    }
}
