package cn.fayostyle.service;

import cn.fayostyle.entity.DinnerTable;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public interface IDinnerTableService {
    /**
     * 查询所有未预定的餐桌
     */
    List<DinnerTable> findNoUseTable();

    /**
     * 根据主键查询
     */
    DinnerTable findById(int id);
}
