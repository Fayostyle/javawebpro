package cn.fayostyle.dao;

import cn.fayostyle.entity.Food;
import cn.fayostyle.utils.PageBean;

/** 菜品管理
 * Created by HuangPan on 2017/6/10.
 */
public interface IFoodDao {
    /**
     * 分页且按条件查询所有的菜品
     */
    void getAll(PageBean<Food> pb);

    /**
     * 按条件统计菜品的总记录数
     */
    int getTotalCount(PageBean<Food> pb);

    /**
     * 根据id查询
     */
    Food findById(int id);
}
