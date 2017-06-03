package cn.fayostyle.dao.impl;

import cn.fayostyle.dao.IDinnerTableDao;
import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.entity.TableStatus;
import cn.fayostyle.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public class DinnerTableDao implements IDinnerTableDao {
    @Override
    public List<DinnerTable> findByStatus(TableStatus ts) {
        String sql = "select * from dinnertable where tableStatus=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), ts.ordinal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable findById(int id) {

        String sql = "select * from dinnertable where id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
