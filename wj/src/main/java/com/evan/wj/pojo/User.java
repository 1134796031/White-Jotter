package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

// @Entity将User类注释为实体类、@Table(name=“user”)指定使用名为user的表
// 为了简化对数据库的操作，使用了JavaPersistenceAPI(JPA)，具体地，前后端使用json格式数据来完成数据交互
// 本项目使用JPA以实现实体类的持久化，而JPA工作时会创造继承实体类User的代理类，并添加两个无须json化的属性handler和hibernateLazyInitializer
// 为了使User转换为json格式时忽视这两个属性，需要使用注解JsonIgnoreProperties将这两个属性忽视

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String username;
    String password;
    String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

