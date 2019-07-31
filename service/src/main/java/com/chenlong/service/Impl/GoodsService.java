package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.Goods;
import com.chenlong.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {


    @Transactional(readOnly = true)
    public List<Goods> getByTypeId(String goodsTypeId) {
        QueryWrapper<Goods> goodsWrapper = new QueryWrapper<>();
        if(!"0".equals(goodsTypeId)){
            goodsWrapper.lambda().eq(Goods::getGoodsTypeId,goodsTypeId);
        }
        List<Goods> list = this.list(goodsWrapper);
        return list;
    }

    @Transactional
    public void upGoods(String id) {//1
        UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<>();
    }

    @Transactional
    public void downGoods(String id) {//2
    }

    @Transactional
    public void delGoods(String id) {
    }
}
