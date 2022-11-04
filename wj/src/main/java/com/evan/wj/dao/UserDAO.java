package com.evan.wj.dao;

// DataAccessObject(数据访问对象,DAO)，即用来操作数据库的对象，该对象可以自己实现，也可以由框架提供
// 这里实现user实体类的DAO接口UserDAO，由于这个接口继承自JpaRepository
// 其中封装了SQL语句，只需按照规范提供方法的名字即可实现对MySQL表的增删改查
// 如findByUsername，即通过输入username字段查询到对应的行，并返回对应的User类
// 此外还可以通过输入如username字段和password字段来返回对应的User类

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);
}

