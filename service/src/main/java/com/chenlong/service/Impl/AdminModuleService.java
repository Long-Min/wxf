package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.AdminModule;
import com.chenlong.entity.vo.JqueryTreeView;
import com.chenlong.mapper.AdminModuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminModuleService extends ServiceImpl<AdminModuleMapper, AdminModule> {
    @Transactional(readOnly = true)
    public List<JqueryTreeView> getModuleByAdminId(Long adminId) {
        return this.baseMapper.getModuleByAdminId(adminId);
    }


}
