package com.example.carrentalcontract.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.apache.commons.beanutils.PropertyUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-30 16:52
 **/


public abstract class DbServiceImpl<T> implements DbService<T> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected DbMapper<T> mapper;
    // @Autowired
    // protected ObjectIdGenerator objectIdGenerator;
    protected String idMethod = "setId";
    protected String getIdMethod = "getId";
    protected String flagMethod = "setFlag";
    protected String updateTimeMethod = "setUpdateTime";
    protected String createTimeMethod = "setCreateTime";
    protected String createTimeField = "createTime";
    protected String searchCodeField = "searchCode";

    public DbServiceImpl() {
    }

    public static String processLikeCharacter(String character) {
        return !StringUtils.isEmpty(character) ? character.replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_") : character;
    }
    @Override
    public Result<T> insert(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            t = this.setDefaultFieldValues(t);
            this.setSearchCode(t);
            int i = this.mapper.insert(t);
            return i == 1 ? new Result(t) : this.fail();
        }
    }
    @Override
    public Result<Long> insertOne(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            int i = this.mapper.insert(t);
            return i == 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result insertBatch(@NonNull List<T> list) {
        if (list == null) {
            throw new NullPointerException("list is marked non-null but is null");
        } else {
            list.forEach(item ->item =this.setDefaultFieldValues(item));
            int i = this.mapper.insertBatch(list);
            return i >= 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result update(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            this.setFieldValue(t, this.updateTimeMethod, Calendar.getInstance().getTime());
            this.setSearchCode(t);
            int i = this.mapper.updateByPrimaryKeySelective(t);
            return i == 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result updateByExample(@NonNull T t, @NonNull Example example) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else if (example == null) {
            throw new NullPointerException("example is marked non-null but is null");
        } else {
            this.setFieldValue(t, this.updateTimeMethod, Calendar.getInstance().getTime());
            int i = this.mapper.updateByExampleSelective(t, example);
            return i > 0 ? new Result() : this.fail();
        }
    }

    public Result updateBatch(@NonNull List<T> list) {
        if (list == null) {
            throw new NullPointerException("list is marked non-null but is null");
        } else {
            Map<String, Object> map = new HashMap();
            map.put("list", list);
            int i = this.mapper.updateBatch(map);
            return i >= 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result delete(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            this.setFieldValue(t, this.flagMethod, 0);
            int i = this.mapper.updateByPrimaryKeySelective(t);
            return i == 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result destroy(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            int i = this.mapper.delete(t);
            return i == 1 ? new Result() : this.fail();
        }
    }
    @Override
    public Result<T> selectByPrimaryKey(@NonNull Long id) {
        if (id == null) {
            throw new NullPointerException("id is marked non-null but is null");
        } else {
            return new Result(this.mapper.selectByPrimaryKey(id));
        }
    }
    @Override
    public Result<Integer> selectCount(T query) {
        this.setFieldValue(query, this.flagMethod, 1);
        int count = this.mapper.selectCount(query);
        return Result.success(count);
    }

    @Override
    public Result<Integer> selectCountByExample(Weekend<T> weekend) {
        int count = this.mapper.selectCountByExample(weekend);
        return Result.success(count);
    }
    @Override
    public Result<T> selectOne(@NonNull T query) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else {
            this.setFieldValue(query, this.flagMethod, 1);
            return new Result(this.mapper.selectOne(query));
        }
    }
    @Override
    public Result<List<T>> select(@NonNull T query) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else {
            this.setFieldValue(query, this.flagMethod, 1);
            return this.select(query, this.createTimeField);
        }
    }

    public Result<List<T>> select(@NonNull Weekend<T> query) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else {
            return new Result(this.mapper.selectByExample(query));
        }
    }
    @Override
    public Result<List<T>> select(@NonNull T query, @NonNull String orderField) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else if (orderField == null) {
            throw new NullPointerException("orderField is marked non-null but is null");
        } else {
            return this.select(query, orderField, true);
        }
    }

    public Result<List<T>> select(@NonNull T query, @NonNull String orderField, boolean isDesc) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else if (orderField == null) {
            throw new NullPointerException("orderField is marked non-null but is null");
        } else {
            this.setFieldValue(query, this.flagMethod, 1);
            Weekend<T> weekend = this.getWeekend(query, orderField, isDesc);
            if (weekend == null) {
                return this.fail();
            } else {
                List<T> result = this.mapper.selectByExample(weekend);
                return new Result(result);
            }
        }
    }
    @Override
    public Result<List<T>> select(Example example) {
        return new Result(this.mapper.selectByExample(example));
    }
    @Override
    public Result<List<T>> selectAll() {
        return new Result(this.mapper.selectAll());
    }
    @Override
    public Result<PageInfo<T>> selectPage(@NonNull T query, @NonNull int pageNum, @NonNull int pageSize) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else {
            this.setFieldValue(query, this.flagMethod, 1);
            return this.selectPage(query, pageNum, pageSize, this.createTimeField);
        }
    }
    @Override
    public Result<PageInfo<T>> selectPage(@NonNull T query, @NonNull int pageNum, @NonNull int pageSize, @NonNull String orderField) {
        if (query == null) {
            throw new NullPointerException("query is marked non-null but is null");
        } else if (orderField == null) {
            throw new NullPointerException("orderField is marked non-null but is null");
        } else {
            this.setFieldValue(query, this.flagMethod, 1);
            if (pageNum < 1) {
                pageNum = 1;
            }

            if (pageSize < 1) {
                pageSize = 1;
            }

            Weekend<T> weekend = this.getWeekend(query, orderField);
            return weekend == null ? this.fail() : ((DbServiceImpl) ObjectContainer.getObject(this.getClass())).selectPage(weekend, pageNum, pageSize, orderField, true);
        }
    }
    @Override
    public Result<PageInfo<T>> selectPage(@NonNull Weekend<T> weekend, @NonNull int pageNum, @NonNull int pageSize) {
        if (weekend == null) {
            throw new NullPointerException("weekend is marked non-null but is null");
        } else {
            return ((DbServiceImpl)ObjectContainer.getObject(this.getClass())).selectPage(weekend, pageNum, pageSize, this.createTimeField, true);
        }
    }
    @Override
    public Result<PageInfo<T>> selectPage(@NonNull Weekend<T> weekend, @NonNull int pageNum, @NonNull int pageSize, @NonNull String orderField, @NonNull boolean isDesc) {
        if (weekend == null) {
            throw new NullPointerException("weekend is marked non-null but is null");
        } else if (orderField == null) {
            throw new NullPointerException("orderField is marked non-null but is null");
        } else {
            if (pageNum < 1) {
                pageNum = 1;
            }

            if (pageSize < 1) {
                pageSize = 1;
            }

            if (isDesc) {
                weekend.orderBy(orderField).desc();
            } else {
                weekend.orderBy(orderField).asc();
            }

            PageRowBounds pageRowBounds = new PageRowBounds((pageNum - 1) * pageSize, pageSize);
            List<T> result = this.mapper.selectByExampleAndRowBounds(weekend, pageRowBounds);
            PageInfo<T> pageInfo = new PageInfo(result);
            return new Result(pageInfo);
        }
    }



    private Class<T> getType() {
        try {
            ParameterizedType superClass = (ParameterizedType)this.getClass().getGenericSuperclass();
            Class<T> type = (Class)superClass.getActualTypeArguments()[0];
            return type;
        } catch (Exception var3) {
            return null;
        }
    }

    protected void setFieldValue(T obj, String fieldName, Object value) {
        Class clazz = obj.getClass();

        try {
            Method method = clazz.getMethod(fieldName, value.getClass());
            if (method == null) {
                return;
            }

            if (fieldName.equals(this.idMethod)) {
                Method getMethod = clazz.getMethod(this.getIdMethod);
                Object id = getMethod.invoke(obj);
                if (id == null) {
                    method.invoke(obj, value);
                }
            } else {
                method.invoke(obj, value);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var8) {
            this.logger.error(var8.getMessage(), var8);
        }

    }

    protected Weekend<T> getWeekend(T query, String orderField) {
        return this.createQuery(query, orderField);
    }

    protected Weekend<T> getWeekend(T query, String orderField, boolean isDesc) {
        return this.createQuery(query, orderField, isDesc);
    }

    protected T setDefaultFieldValues(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            // 千万级别数据id可以不重复
            String machineId = "11";
            SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
            String dayTime = sdf.format(new Date());
            int hashCode = UUID.randomUUID().toString().hashCode();
            if(hashCode < 0){
                hashCode = -hashCode;
            }
            String value = machineId + dayTime + String.format("%010d", hashCode);
            // System.out.println(value);

            // long id = this.objectIdGenerator.newId();
            this.setFieldValue(t, this.idMethod, value);
            // UUID uuid = UUID.randomUUID();
            this.setFieldValue(t, this.flagMethod, 1);
            this.setFieldValue(t, this.createTimeMethod, Calendar.getInstance().getTime());
            return t;
        }
    }

    protected void setSearchCode(@NonNull T t) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else {
            this.setSearchCode(t, "name");
        }
    }

    protected void setSearchCode(@NonNull T t, String nameField) {
        if (t == null) {
            throw new NullPointerException("t is marked non-null but is null");
        } else if (t != null) {
            Map<String, Field> entityFields = new HashMap();
            Class<?> clazz = t.getClass();
            List<Field> allField = this.getAllField(clazz);
            Iterator var6 = allField.iterator();

            Field scField;
            while(var6.hasNext()) {
                scField = (Field)var6.next();
                scField.setAccessible(true);
                entityFields.put(scField.getName(), scField);
            }

            if (entityFields.containsKey(nameField) && entityFields.containsKey(this.searchCodeField)) {
                Field nField = (Field)entityFields.get(nameField);
                scField = (Field)entityFields.get(this.searchCodeField);

                try {
                    if (!nField.getType().equals(String.class)) {
                        return;
                    }

                    if (!scField.getType().equals(String.class)) {
                        return;
                    }

                    String nameVal = (String)nField.get(t);
                    if (nameVal == null) {
                        return;
                    }

                    String searchCodeVal = (String)scField.get(t);
                    if (searchCodeVal != null) {
                        return;
                    }

                    scField.set(t, ChineseCharacterHelper.getSpells(nameVal));
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

        }
    }

    private List<Field> getAllField(Class clazz) {
        List<Field> fields = new ArrayList();

        for(Class tempClazz = clazz; tempClazz != null; tempClazz = tempClazz.getSuperclass()) {
            Field[] var4 = tempClazz.getDeclaredFields();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field declaredField = var4[var6];
                fields.add(declaredField);
            }
        }

        return fields;
    }

    private Result fail() {
        return this.fail((Object)null);
    }

    private <T> Result<T> fail(T data) {
        return new Result(700, "数据库操作失败！", data);
    }
    @Override
    public Result<List<T>> selectIn(Class<T> clazz, Collection<Long> idList) {
        return this.privateSelectIn(clazz, idList);
    }
    @Override
    public Result<List<T>> selectIn(Class<T> clazz, List<Long> idList) {
        return this.privateSelectIn(clazz, idList);
    }
    @Override
    public Result<List<T>> selectIn(Collection<Long> idList) {
        return this.privateSelectIn(this.getType(), idList);
    }

    private Result<List<T>> privateSelectIn(Class<T> clazz, Collection<Long> idList) {
        Example e = new Example(clazz);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("flag", 1).andIn("id", idList);
        return this.select(e);
    }
    @Override
    public Result<List<T>> selectNotIn(Class<T> clazz, Collection<Long> idList) {
        return this.privateSelectNotIn(clazz, idList);
    }
    @Override
    public Result<List<T>> selectNotIn(Class<T> clazz, List<Long> idList) {
        return this.privateSelectNotIn(clazz, idList);
    }
    @Override
    public Result<List<T>> selectNotIn(Collection<Long> idList) {
        return this.privateSelectNotIn(this.getType(), idList);
    }

    private Result<List<T>> privateSelectNotIn(Class<T> clazz, Collection<Long> idList) {
        Example e = new Example(clazz);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("flag", 1).andNotIn("id", idList);
        return this.select(e);
    }

    public <T> Weekend<T> createQuery(T condition, Date beginTime, Date endTime) {
        return this.createQuery(condition, beginTime, endTime, "createTime");
    }

    protected <T> Weekend<T> createQuery(T condition, Date beginTime, Date endTime, String timePropertyName) {
        Weekend<T> weekend = this.createQuery(condition, true);
        weekend.and().andBetween(timePropertyName, beginTime, endTime);
        return weekend;
    }

    protected <T> Weekend<T> createQuery(T condition) {
        return this.createQuery(condition, false);
    }

    protected <T> Weekend<T> createQuery(T condition, boolean excludeTimeProperties) {
        Weekend<T> weekend = new Weekend(condition.getClass(), Boolean.TRUE);
        WeekendCriteria criteria = weekend.weekendCriteria();

        try {
            Map<String, Object> describe = PropertyUtils.describe(condition);
            Iterator var6 = describe.entrySet().iterator();

            while(true) {
                Map.Entry entry;
                Object value;
                do {
                    do {
                        do {
                            if (!var6.hasNext()) {
                                return weekend;
                            }

                            entry = (Map.Entry)var6.next();
                            value = entry.getValue();
                        } while(value == null);
                    } while(value == null || "".equals(value) || "null".equals(value));
                } while(excludeTimeProperties && value instanceof Date);

                if (!(value instanceof Class) && !"pageNum".equals(entry.getKey()) && !"pageSize".equals(entry.getKey()) && !"total".equals(entry.getKey()) && !"beginIndex".equals(entry.getKey()) && !"maxSize".equals(entry.getKey())) {
                    criteria.andEqualTo((String)entry.getKey(), value);
                }
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var9) {
            return null;
        }
    }

    protected <T> Weekend<T> createQuery(T condition, String orderField) {
        return this.createQuery(condition, orderField, true);
    }

    protected <T> Weekend<T> createQuery(T condition, String orderField, boolean isDesc) {
        Weekend<T> weekend = this.createQuery(condition);
        if (isDesc) {
            weekend.orderBy(orderField).desc();
        } else {
            weekend.orderBy(orderField).asc();
        }

        return weekend;
    }
}
