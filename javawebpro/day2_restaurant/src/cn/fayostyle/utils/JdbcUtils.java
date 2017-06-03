package cn.fayostyle.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * Created by HuangPan on 2017/5/30.
 */
public class JdbcUtils {
    private static DataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 创建DbUtils常用工具类对象
     */
    public static QueryRunner getQueryRunner() {
        return new QueryRunner(dataSource);
    }
}
