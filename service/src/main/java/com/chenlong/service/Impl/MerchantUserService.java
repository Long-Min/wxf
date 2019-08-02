package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.MerchantUser;
import com.chenlong.mapper.MerchantUserMapper;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserService extends ServiceImpl<MerchantUserMapper, MerchantUser> {
    public MerchantUser getUserByName(String username) {

        QueryWrapper<MerchantUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MerchantUser::getUserName,username);
        return this.getOne(wrapper);
    }
}
