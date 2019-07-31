package com.chenlong.admin.controller;

import com.chenlong.common.http.Result;
import com.chenlong.entity.dto.Admin;
import com.chenlong.entity.dto.AdminModule;
import com.chenlong.entity.dto.AdminModuleRole;
import com.chenlong.entity.dto.AdminRole;
import com.chenlong.service.Impl.AdminModuleRoleService;
import com.chenlong.service.Impl.AdminModuleService;
import com.chenlong.service.Impl.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    AdminModuleService adminModuleService;

    @Autowired
    AdminModuleRoleService adminModuleRoleService;

    @RequestMapping("list")
    public ModelAndView toList(Admin admin){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/list");

        List<AdminRole> roleList = adminRoleService.list();
        modelAndView.addObject("roleList",roleList);
        List<AdminModule> moduleList = adminModuleService.list();
        modelAndView.addObject("moduleList",moduleList);
        return modelAndView;
    }

    public class RoleInfo{
        private AdminRole adminRole;
        private List<AdminModuleRole> adminModuleRolesList;

        public RoleInfo(AdminRole adminRole, List<AdminModuleRole> adminModuleRolesList) {
            this.adminRole = adminRole;
            this.adminModuleRolesList = adminModuleRolesList;
        }

        public RoleInfo() {
        }

        public AdminRole getAdminRole() {
            return adminRole;
        }

        public void setAdminRole(AdminRole adminRole) {
            this.adminRole = adminRole;
        }

        public List<AdminModuleRole> getAdminModuleRolesList() {
            return adminModuleRolesList;
        }

        public void setAdminModuleRolesList(List<AdminModuleRole> adminModuleRolesList) {
            this.adminModuleRolesList = adminModuleRolesList;
        }
    }

    @RequestMapping("getById")
    @ResponseBody
    public Result<?> getById(@RequestParam(value = "id",required = true) Long id, Admin admin){
        List<AdminModuleRole> adminModuleRolesList = adminModuleRoleService.getRoleById(id);
        AdminRole byId = adminRoleService.getById(id);
        RoleInfo roleInfo = new RoleInfo(byId,adminModuleRolesList);
        return Result.success(roleInfo);
    }

    @RequestMapping("save")
    @ResponseBody
    public Result<?> save(AdminRole adminRole, @RequestParam(value="moduleRoles") String moduleRoles){
        adminRoleService.saveRole(adminRole,moduleRoles);
        return Result.success();
    }

    @RequestMapping("del")
    @ResponseBody
    public Result<?> del(@RequestParam(value = "id")String id){
        adminRoleService.removeById(id);
        return Result.success();
    }

//    @RequestMapping("upload")
//    @ResponseBody
//    public Result<?> upload(MultipartFile file) throws IOException {
//        if(file != null){
//            String fileName = file.getOriginalFilename();
//            System.out.println(fileName);
//            File fileDir = new File("C:/Users/word/Desktop/"+fileName);
//            file.transferTo(fileDir);
//            return Result.success();
//        }
//        return Result.error();
//    }
}
