package com.evan.wj.service;

import com.evan.wj.dao.BookDAO;
import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    CategoryService categoryService;

    // 查，全部书籍
    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDAO.findAll(sort);
    }

    // 查，指定书籍的cid，对BookDAO.java中函数的封装
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    // 查，指定书籍的书名或人名的模糊信息，对BookDAO.java中函数的封装
    public List<Book> Search(String keywords) {
        // 事实上，由于BookDAO中的函数findAllByTitleLikeOrAuthorLike是继承自SpringBoot框架的模糊查询算法，必须设置两个输入，分别表示标题名和人名
        // 但用户在输入模糊信息时往往只会输入一个，所以这里在Service对DAO封装时，将同一个输入的字符串keywords重复使用了两次
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }

    // 增/改，save()方法的逻辑是当主键存在时更新数据，当主键不存在时插入数据
    public void addOrUpdate(Book book) {
        bookDAO.save(book);
    }

    // 删
    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }
}

