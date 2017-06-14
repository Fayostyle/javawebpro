package cn.fayostyle.dao.impl;

import cn.fayostyle.dao.IDinnerTableDao;
import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public class DinnerTableDao implements IDinnerTableDao {

    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(DinnerTable dt) {
        String sql = "insert into dinnertable(tableName) values(?)";
        try {
            qr.update(sql, dt.getTableName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from dinnertable where id=?";
        try {
            qr.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DinnerTable dt) {
        String sql = "update dinnertable set tableStatus=?,orderDate=? where id=?";
        try {
            qr.update(sql, dt.getTableStatus(), dt.getOrderDate(), dt.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> query() {
        String sql = "select * from dinnertable";
        try {
            return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinnertable where id=?";
        try {
            return qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> query(String keyword) {
        String sql = "select * from dinnertable where tableName like ?";
        try {
            return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%" + keyword + "%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void quitTable(int id) {
        String sql = "update dinnertable set tableStatus=?,orderDate=? where id=?";
        try {
            qr.update(sql, 0, null, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
