package com.edu118.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.edu118.validateGroup.IAddGroup;
import com.edu118.validateGroup.ILoginGroup;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "emp")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 员工id
    */
    @Pattern(regexp = "^[a-zA-Z]\\w{3,12}$",message = "您提供的用户名格式错误！",groups = {IAddGroup.class, ILoginGroup.class})
    private String eid;
    /**
    * 职务id
    */
    @NotBlank(message = "请您提供当前用户的职位信息！！",groups = {IAddGroup.class})
    private String lid;
    /**
    * 部门id
    */
    @NotNull(message = "请您提供当前用户的部门信息！",groups = {IAddGroup.class})
    @Min(value = 1,message = "请您提供当前用户的部门信息！",groups = {IAddGroup.class})
    private Long did;
    /**
    * 员工姓名
    */
    @NotBlank(message = "请您提供当前用户的姓名！",groups = {IAddGroup.class})
    private String ename;
    /**
    * 薪资
    */
    private Double sal;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 登陆密码
    */
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "您提供的用户密码格式错误！",groups = {IAddGroup.class, ILoginGroup.class})
    private String password;
    /**
    * 相片(默认为"xyd.png")
    */
    private String photo;
    /**
    * 备注
    */
    private String note;
    /**
    * 入职日期
    */
    private String hiredate;
    /**
    * ineid
    */
    private String ineid;
    /**
    * 员工编号
    */
    @Column(name = "enum")
    private String empNumber;
    /**
    * locked = 0 正常 locked = 1 锁定 locked = 2 停用
         * 默认给locked=0
    */
    private Integer locked = 0;
}
