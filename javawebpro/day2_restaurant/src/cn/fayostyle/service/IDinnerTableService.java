package cn.fayostyle.service;

import cn.fayostyle.entity.DinnerTable;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public interface IDinnerTableService {
    /**************************  V2.0版本   ************************/
//    /**
//     * 查询所有未预定的餐桌
//     */
//    List<DinnerTable> findNoUseTable();
//
//    /**
//     * 根据主键查询
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
