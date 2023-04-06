package com.by.base;

import com.by.util.CastUtil;
import com.by.base.BaseDao;
import com.by.base.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Map;

@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseDao<T> getEntityDao();

    public Integer insert(T t) {
        return getEntityDao().insert(t);
    }

    public void delete(Long id) {
        getEntityDao().delete(id);
    }

    public Integer update(T t) {
        return getEntityDao().update(t);
    }

    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }

    public PageInfo<T> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        /*
        * 开启分页功能，将这两个参数 与当前线程进行绑定，传递给dao层
        * startIndex = (pageNum-1)*pageSize
        * select 语句 最后自动增加 limit ?,?  limit startIndex,pageIndex
        * */
        PageHelper.startPage(pageNum, pageSize);
        Page<T> page = getEntityDao().findPage(filters);
        return new PageInfo<T>(page , 10);
    }
}