package cn.ccsu.service.impl;

import cn.ccsu.entity.Goods;
import cn.ccsu.entity.OutStore;
import cn.ccsu.entity.OutStoreDetail;
import cn.ccsu.entity.OutStoreLink;
import cn.ccsu.enums.AppHttpCodeEnum;
import cn.ccsu.ex.SystemException;
import cn.ccsu.mapper.GoodsMapper;
import cn.ccsu.mapper.OutStoreDetailMapper;
import cn.ccsu.mapper.OutStoreLinkMapper;
import cn.ccsu.mapper.OutStoreMapper;
import cn.ccsu.service.OutStoreService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreLinkMapper outStoreLinkMapper;
    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private OutStoreDetailMapper outStoreDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult outStore(OutStore outStore) {
        outStore.setCreateTime(new Date());
        outStore.setNumber(UUID.randomUUID().toString().replaceAll("_",""));
        outStore.setIsDelete(1);
        outStore.setUpdateTime(new Date());
        Integer integer = outStoreMapper.insert(outStore);
        Integer id = outStore.getId();
        if (integer!=1){
            throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR);
        }
        List<OutStoreDetail> detail = outStore.getDetail();
        for (OutStoreDetail outStoreDetail:detail){

            Integer goodsId = outStoreDetail.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            if (Objects.isNull(goods)){
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
            }
            outStoreDetail.setName(goods.getName());
            outStoreDetail.setColor(goods.getColor());
            outStoreDetail.setSize(goods.getSize());
            outStoreDetail.setColor(goods.getColor());
            outStoreDetail.setCreateTime(new Date());
            outStoreDetail.setUpdateTime(new Date());
            Integer insert = outStoreDetailMapper.insert(outStoreDetail);
            if (insert!=1){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR2);
            }
            Integer count = outStoreDetail.getCount();
            if (count>goods.getCount()){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR3);

            }
            count=goods.getCount()-count;
            Integer integer1 = goodsMapper.updateCount(goodsId, count);
            if (integer1!=1){
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
            }
            OutStoreLink outStoreLink=new OutStoreLink();
            outStoreLink.setOutStoreId(id);
            outStoreLink.setOutStoreDetailId(outStoreDetail.getId());
            Integer insert1 = outStoreLinkMapper.insert(outStoreLink);
            if (insert1!=1){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR4);
            }


        }

        return ResponseResult.okResult();
    }



}
