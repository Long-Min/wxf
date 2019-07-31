package com.chenlong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenlong.entity.dto.AdminModule;
import com.chenlong.entity.vo.JqueryTreeView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminModuleMapper extends BaseMapper<AdminModule> {
    List<JqueryTreeView> getModuleByAdminId(@Param("adminId") Long adminId);
}
