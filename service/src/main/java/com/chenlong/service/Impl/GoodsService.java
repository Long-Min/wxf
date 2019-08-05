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

import java.util.ArrayList;
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
        this.saveOrUpdate(goods);
        Long id = goods.getId();
        if(goods.getId() != null){
            UpdateWrapper<GoodsSku> goodsSkuUpdateWrapper = new UpdateWrapper<>();
            goodsSkuUpdateWrapper.lambda().eq(GoodsSku::getGoodId,id);
            goodsSkuService.remove(goodsSkuUpdateWrapper);//删除商品类型
        }
        /*
             保存
          保存或更新商品
          得到商品id
          拆分字符串
          保存商品类型
         */
        String[] title = goods.getSkuTitle().split("\\|");
        String[] cost = goods.getSkuCost().split("\\|");
        String[] price = goods.getSkuPrice().split("\\|");
        String[] pmoney = goods.getSkuPmoney().split("\\|");

        ArrayList<GoodsSku> goodsSkuArrayList = new ArrayList<GoodsSku>(title.length);

        Long count = 1L;
        for (int i = 0; i < title.length;i++ ){
            if(!"|".equals(title[i])){
                GoodsSku goodsSku = new GoodsSku();
                goodsSku.setTitle(title[i]);
                goodsSku.setCost(cost[i]);
                goodsSku.setPrice(price[i]);
                goodsSku.setPmoney(pmoney[i]);
                goodsSku.setGoodId(id);
                goodsSku.setOrderNo(count);
                goodsSkuArrayList.add(goodsSku);
            }
        }
        /*
             增加
          保存或更新商品
          得到商品id
            删除商品类型
          拆分字符串
          保存商品类型
         */
        goodsSkuService.saveBatch(goodsSkuArrayList);
    }


    @Transactional(readOnly = true)
    public List<Goods> getUpGoodsById(Long id) {
        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
        goodsQueryWrapper.lambda()
                .eq(Goods::getMerchantUserId,id)
                .eq(Goods::getState,Goods.State.UP.getCode());
        return this.list(goodsQueryWrapper);
    }
}
