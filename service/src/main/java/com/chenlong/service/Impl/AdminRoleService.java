package com.chenlong.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlong.entity.dto.AdminModuleRole;
import com.chenlong.entity.dto.AdminRole;
import com.chenlong.mapper.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleService extends ServiceImpl<AdminRoleMapper, AdminRole> {

    @Autowired
    private AdminModuleRoleService adminModuleRoleService;

    @Transactional
    public void saveRole(AdminRole adminRole, String moduleRoles) {
        this.saveOrUpdate(adminRole);
        if(moduleRoles != null){
            QueryWrapper<AdminModuleRole> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.lambda().eq(AdminModuleRole::getAdminRoleId,adminRole.getId());
            adminModuleRoleService.remove(deleteWrapper);
        }

        String[] split = moduleRoles.split("|");
        List<AdminModuleRole> needInsert = new ArrayList<>();
        for (String moduleId: split) {
            if(!"|".equals(moduleId)) {
                AdminModuleRole adminModuleRole = new AdminModuleRole();
                adminModuleRole.setAdminRoleId(adminRole.getId());
                adminModuleRole.setAdminModuleId(Long.parseLong(moduleId));
                needInsert.add(adminModuleRole);
            }
        }

        adminModuleRoleService.saveBatch(needInsert,needInsert.size());
    }
}
