package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionsDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url})")
    public void save(Permission permission) throws Exception;


    @Select("select * from permission where id=#{permissionId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionname"),
            @Result(property = "url", column = "url"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
            many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByPermissionId"))
    })
    public Permission findPermissionById(String permissionId);

}
