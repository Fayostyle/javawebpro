package cn.fayostyle.dao.impl;

import cn.fayostyle.dao.IFoodTypeDao;
import cn.fayostyle.entity.FoodType;
import cn.fayostyle.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by HuangPan on 2017/5/30.
 */
public class FoodTypeDao implements IFoodTypeDao {

    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(FoodType foodType) {
        String sql = "insert into foodtype(typeName) values(?) ";
        try {
            qr.update(sql, foodType.getTypeName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from foodtype where id=?";
        try {
            qr.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        String sql = "update foodtype set typeName=? where id=?";
        try {
            qr.update(sql, foodType.getTypeName(), foodType.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> query() {
        String sql = "select * from foodtype";
        try {
            return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FoodType findById(int id) {
        String sql = "select * from foodtype where id=?";
        try {
          return qr.query(sql, new BeanHandler<FoodType>(FoodType.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> query(String keyword) {
        String sql = "select * from foodtype where typeName like ?";
        try {
            return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class), "%" + keyword + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getFirstType() {
        String sql = "select * from foodtype";
        try {
            return qr.query(sql, new ScalarHandler<Integer>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
