package cn.fayostyle.dao;

import cn.fayostyle.entity.DinnerTable;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public interface IDinnerTableDao {
    /*************************  V1.0版本  *********************/
//    /**
//     * 根据预定状态查询
//     */
//    List<DinnerTable> findByStatus(TableStatus ts);
//
//    /**
//     * 主键查询
//     */
//    DinnerTable findById(int id);

    /*************************  V2.0版本   *******************/
    void add(DinnerTable dt);
    void delete(int id);
    void update(DinnerTable dt);
    List<DinnerTable> query();
    DinnerTable findById(int id);
    List<DinnerTable> query(String keyword);
    //退桌操作
    void quitTable(int id);

}
