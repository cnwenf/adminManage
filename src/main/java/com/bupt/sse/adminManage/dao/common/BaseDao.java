package com.bupt.sse.adminManage.dao.common;

/**
 * Created by WenFe on 2017/3/15.
 */
import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO 定义DAO的通用操作
 *
 * @author Monday
 */
public interface BaseDao<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(Serializable id);

    public List<T> list();

    public T getById(Serializable id);

    public List<T> findByHQL(String hql, Object... params);

}
