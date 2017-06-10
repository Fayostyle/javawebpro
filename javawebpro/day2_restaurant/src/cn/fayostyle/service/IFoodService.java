package cn.fayostyle.service;

import cn.fayostyle.entity.Food;
import cn.fayostyle.utils.PageBean;

/**
 * Created by HuangPan on 2017/6/11.
 */
public interface IFoodService {
    /**
     * 主键查询
     */
    Food findById(int id);

    /**
     * 分页查询
     */
    void getAll(PageBean<Food> pb);
}
