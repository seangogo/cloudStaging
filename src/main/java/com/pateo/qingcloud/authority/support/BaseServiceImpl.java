package com.pateo.qingcloud.authority.support;

import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author jh
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseRepository<T, ID> getBaseDao();

    @Override
    public T find(ID id) {
        return getBaseDao().findByIdAndDelFlag(id,0);
    }

    @Override	
    public List<T> findAll() {
        return getBaseDao().findAll();
    }


    @Override
    public List<T> findList(Specification<T> spec, Sort sort) {
        return getBaseDao().findAll(spec, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseDao().findAll(pageable);
    }

    @Override
    public long count() {
        return getBaseDao().count();
    }

    @Override
    public long count(Specification<T> spec) {
        return getBaseDao().count(spec);
    }

    @Override
    public boolean exists(ID id) {
        return getBaseDao().exists(id);
    }

    @Override
    public void save(T entity) {
        getBaseDao().save(entity);
    }

    public void save(Iterable<T> entitys) {
        getBaseDao().save(entitys);
    }

    @Override
    public T update(T entity) {
        return getBaseDao().saveAndFlush(entity);
    }

    @Override
    public void delete(ID id) throws DBException{
        T t=getBaseDao().findOne(id);
        if (t==null){
            throw new DBException(ResultEnum.DATA_NOT_EXIST);
        }
        t.setDelFlag(1);
        getBaseDao().save(t);
    }

    @Override
    public void deleteByIds(@SuppressWarnings("unchecked") ID... ids) {
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                ID id = ids[i];
                this.delete(id);
            }
        }
    }

    @Override
    public void delete(T[] entitys) {
        List<T> tList = Arrays.asList(entitys);
        getBaseDao().delete(tList);
    }

    @Override
    public void delete(Iterable<T> entitys) {
        getBaseDao().delete(entitys);
    }

    @Override
    public void delete(T entity) {
        getBaseDao().delete(entity);
    }

   /* @Override
    public List<T> findList(Iterable<ID> ids) {
        return getBaseDao().findAll(ids);
    }*/

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        // TODO Auto-generated method stub
        return getBaseDao().findAll(spec, pageable);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return getBaseDao().findAll(example, pageable);
    }



}
