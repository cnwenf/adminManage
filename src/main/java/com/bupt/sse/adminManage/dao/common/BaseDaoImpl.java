package com.bupt.sse.adminManage.dao.common;

/**
 * Created by WenFe on 2017/3/15.
 */
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;

/**
 * Created by WenFeng on 2017/3/15.
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private Class<T> clazz;

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * 向DAO层注入SessionFactory
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * 获取当前工作的Session
     */
    protected Session openSession() {
        return sessionFactory.openSession();
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void create(T entity) {
        Session session = this.openSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public T update(T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } catch(StaleStateException exception){
            exception.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }

        return entity;
    }

    public void deleteById(Serializable id) {
        T entity = getById(id);
        delete(entity);
    }

    public void delete(T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch(StaleStateException exception){
            exception.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<T> list() {
        Session session = this.openSession();
        List<T> tmpList = new ArrayList<T>();
        try {
            session.beginTransaction();
            tmpList = session.createQuery("from " + clazz.getName()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            session.close();
        }

        return tmpList;
    }

    public T getById(Serializable id) {
        return (T) this.getSession().get(this.clazz, id);
    }

    public List<T> findByHQL(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params);
        }
        return query.list();
    }
}
