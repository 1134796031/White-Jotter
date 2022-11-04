package com.evan.wj.dao;

import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {
    // 在Book.java中对Book类的定义，以Category对象作为Book对象cid
    // 1、findAllByCategory是以Category对象作为查询条件
    // 2、findAllByTitleLikeOrAuthorLike是根据标题或作者进行模糊查询，参数是两个String分别是标题和作者
    // 3、
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
