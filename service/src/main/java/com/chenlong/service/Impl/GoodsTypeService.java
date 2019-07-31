package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.GoodsType;
import com.chenlong.mapper.GoodsTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeService extends ServiceImpl<GoodsTypeMapper, GoodsType> {
    public List<GoodsType> listAsc() {
        QueryWrapper<GoodsType> goodsTypeWrapper = new QueryWrapper<>();
        goodsTypeWrapper.lambda().orderByAsc(GoodsType::getOrderNum);
        return this.list(goodsTypeWrapper);
    }
}
