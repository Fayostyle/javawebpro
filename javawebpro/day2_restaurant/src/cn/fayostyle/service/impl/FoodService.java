package cn.fayostyle.service.impl;

import cn.fayostyle.dao.IFoodDao;
import cn.fayostyle.entity.Food;
import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IFoodService;
import cn.fayostyle.utils.PageBean;

/**
 * Created by HuangPan on 2017/6/11.
 */
public class FoodService implements IFoodService {

    private IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);
    @Override
    public Food findById(int id) {
        return foodDao.findById(id);
    }

    @Override
    public void getAll(PageBean<Food> pb) {
        foodDao.getAll(pb);
    }
}
