package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.AdminModuleRole;
import com.chenlong.mapper.AdminModuleRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminModuleRoleService extends ServiceImpl<AdminModuleRoleMapper, AdminModuleRole> {
    @Transactional(readOnly = true)
    public List<AdminModuleRole> getRoleById(Long roleId) {
        QueryWrapper<AdminModuleRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AdminModuleRole::getAdminRoleId,roleId);
        return this.list(wrapper);
    }
}
