package com.tranquocanh.repository.custom.impl;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.entity.UserEntity;
import com.tranquocanh.repository.custom.UserRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findAllByStatus(Integer status, UserBuilder builder) {
        StringBuilder sql = new StringBuilder("select * from users as u ");
        if (builder.getCode()!= null && builder.getCode().length > 0) {
            sql.append(" join user_role as ur on u.id = ur.user_id ");
            sql.append(" join role as r on ur.role_id = r.id ");
        }
        sql.append(" where 1=1 ");
        sql = buildSqlQuery(sql,builder);
        Query query = entityManager.createNativeQuery(sql.toString(),UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> findAllByRole() {
        StringBuilder sql = new StringBuilder(" select * from users as u ");
        sql.append(" join user_role as ur on u.id = ur.user_id ");
        sql.append(" join role as r on ur.role_id = r.id ");
        sql.append(" where r.code = 'STAFF' ");
        Query query = entityManager.createNativeQuery(sql.toString(),UserEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlQuery(StringBuilder sql, UserBuilder builder) {
        Field[] fields = builder.getClass().getDeclaredFields();
        for(Field field: fields) {
            String fieldType = field.getType().getName();
            if(fieldType.equals("java.lang.String")) {
                String value = getValue(field,String.class,builder);
                if(StringUtils.isNotBlank(value)) {
                    sql.append(" and lower("+field.getName()+") like '%"+value.toLowerCase()+"%'");
                }
            }
        }
        if (builder.getCode() != null && builder.getCode().length > 0) {
            sql.append(" and lower(r.code) = '"+builder.getCode()[0].toLowerCase()+"' ");
            for (String code: builder.getCode()) {
                if (code != builder.getCode()[0]) {
                    sql.append(" or lower(r.code) = '"+code.toLowerCase()+"' ");
                }
            }
        }
        return sql;

    }

    private <T> T getValue(Field field, Class<T> type, UserBuilder builder) {
        Object result = null;
        Method getter = getGetter(field,builder);
        if(getter != null) {
            try {
                result = getter.invoke(builder);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return type.cast(result);
    }

    private Method getGetter(Field field, UserBuilder builder) {
        String getName = "get" + StringUtils.capitalize(field.getName());
        try {
            return builder.getClass().getMethod(getName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null ;
        }
    }
}
