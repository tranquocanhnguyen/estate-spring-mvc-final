package com.tranquocanh.repository.custom.impl;

import com.tranquocanh.builder.BuildingBuilder;
import com.tranquocanh.entity.BuildingEntity;
import com.tranquocanh.paging.Pageble;
import com.tranquocanh.repository.custom.BuidingRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuidingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(Pageble pageble, BuildingBuilder builder) {
        StringBuilder sql = new StringBuilder("select * from building as b ");
        sql.append(" where 1=1 ");
        sql = buildSqlQuery(sql,builder);
        if(pageble.getSorter().getSortBy() != null && pageble.getSorter().getSortName() != null) {
            sql.append(" order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+" ");
        }
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        if (pageble .getOffset() != null && pageble.getLimit() != null) {
            query.setFirstResult(pageble.getOffset());
            query.setMaxResults(pageble.getLimit());
        }
        return query.getResultList();
    }

    private StringBuilder buildSqlQuery(StringBuilder sql, BuildingBuilder builder) {
        //lấy cá field trong BuildingBuider
        Field[] fields = BuildingBuilder.class.getDeclaredFields();
        for(Field field: fields) {
            //lấy type của field
            String fieldType = field.getType().getTypeName();
            //trường hợp field có type là String
            if(fieldType.equals("java.lang.String")) {
                //lấy giá trị của field
                String value = getValue(field,String.class,builder);
                //nếu field tồn tại giá trị thì add vào sql để tìm kiếm
                if(StringUtils.isNotBlank(value)) {
                    //add vao sql
                    sql.append(" and lower("+field.getName()+") like '%"+value.toLowerCase()+"%'");
                }
            }
            if(fieldType.equals("java.lang.Integer")) {
                Integer value = getValue(field,Integer.class,builder);
                if(value != null && value != 0) {
                    sql.append(" and "+field.getName()+" =  "+value+" ");
                }
            }
            if(builder.getType().length > 0) {
                sql.append(" and lower(type) like '%"+builder.getType()[0].toLowerCase()+"%' ");
                for (String item: builder.getType()) {
                    if(item != builder.getType()[0]) {
                        sql.append(" or lower(type) like '%"+item.toLowerCase()+"%'");
                    }
                }
            }
        }
        return sql;
    }

    private <T> T getValue(Field field, Class<T> type, BuildingBuilder builder) {
        Object result = null;
        //lấy phuong thức Getter của field đó trong BuildingBuider
        Method getter = getGetter(field, builder);
        if(getter != null) {
            //lấy gia trị trong getter method
            try {
                result = getter.invoke(builder);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //giá trị sẽ được ép theo kiểu truyền vào
        return type.cast(result);
    }

    private Method getGetter(Field field, BuildingBuilder builder) {
        //viết hoa ky tự đầu tiên của từ trong chuổi
        String getterName = "get" + StringUtils.capitalize(field.getName());
        try {
            //trả về method
            return builder.getClass().getMethod(getterName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getToTalItem(BuildingBuilder builder) {
        StringBuilder sql= new StringBuilder("select count(*) from building");
        sql.append(" where 1=1 ");
        sql = buildSqlQuery(sql,builder);
        Query query = entityManager.createNativeQuery(sql.toString());
        BigInteger result= (BigInteger)query.getSingleResult();
        return result.intValue() ;
    }
}
