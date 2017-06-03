package cn.fayostyle.dao;

import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.entity.TableStatus;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public interface IDinnerTableDao {
    /**
     * 根据预定状态查询
     */
    List<DinnerTable> findByStatus(TableStatus ts);

    /**
     * 主键查询
     */
    DinnerTable findById(int id);
}
