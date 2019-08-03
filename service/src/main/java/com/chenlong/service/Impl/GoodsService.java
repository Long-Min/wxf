package com.chenlong.service.Impl;

import com.alibaba.druid.util.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.Goods;
import com.chenlong.entity.dto.GoodsSku;
import com.chenlong.mapper.GoodsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Autowired
    GoodsSkuService goodsSkuService;

    @Transactional(readOnly = true)
    public List<Goods> getByTypeId(String goodsTypeId) {
        QueryWrapper<Goods> goodsWrapper = new QueryWrapper<>();
        if (!"0".equals(goodsTypeId)) {
            goodsWrapper.lambda().eq(Goods::getGoodsTypeId, goodsTypeId);
        }
        List<Goods> list = this.list(goodsWrapper);
        return list;
    }

    @Transactional
    public void upGoods(String id) {//1
        UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<>();
        goodsUpdateWrapper.lambda().eq(Goods::getId, id).set(Goods::getState, Goods.State.UP.getCode());
        boolean update = this.update(goodsUpdateWrapper);
    }

    @Transactional
    public void downGoods(String id) {//2
        UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<>();
        goodsUpdateWrapper.lambda().eq(Goods::getId, id).set(Goods::getState, Goods.State.DOWN.getCode());
        boolean update = this.update(goodsUpdateWrapper);
    }

    @Transactional
    public void delGoods(String id) {
        this.removeById(id);
        UpdateWrapper<GoodsSku> goodsSkuUpdateWrapper = new UpdateWrapper<>();
        goodsSkuUpdateWrapper.lambda().eq(GoodsSku::getGoodId,id);
        goodsSkuService.remove(goodsSkuUpdateWrapper);

    }

    //Goods{
    // name='test',
    // merchantUserId=null,
    // goodsTypeId=2,
    // pic='http://localhost:8080/332c36a7-dadf-4d61-8365-7d9d437797ec.png',
    // promoteDesc='test',
    // skuTitle='1.1|2.1',
    // skuCost='1.2|2.2',
    // skuPrice='1.3|2.3',
    // skuPmoney='1.4|2.4',
    // orderNum=4,
    // state=null,
    // createTime=null}

    @Transactional
    public void saveGoods(Goods goods) {
        if(goods.getId() != null){
            String s = goods.getId().toString();
            this.delGoods(s);//删除商品
            UpdateWrapper<GoodsSku> goodsSkuUpdateWrapper = new UpdateWrapper<>();
            goodsSkuUpdateWrapper.lambda().eq(GoodsSku::getGoodId,s);
            goodsSkuService.remove(goodsSkuUpdateWrapper);//删除商品类型
        }
        /*
        保存
        先
          删除商品
          删除商品类型
        再
          保存商品
          得到商品id
          拆分字符串
          保存商品类型
         */

        /*
        增加
        先
          保存商品
          得到商品id
          拆分字符串
          保存商品类型
         */

        GoodsSku goodsSku = new GoodsSku();
        BeanUtils.copyProperties(goods,goodsSku);
        //goodsSku.get



        //this.save(goods);
        //goodsSkuService.save(null);
    }
}
