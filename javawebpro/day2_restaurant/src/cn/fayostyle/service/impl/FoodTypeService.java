package cn.fayostyle.service.impl;

import cn.fayostyle.dao.IFoodTypeDao;
import cn.fayostyle.entity.FoodType;
import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IFoodTypeService;

import java.util.List;

/**
 * Created by HuangPan on 2017/5/31.
 */
public class FoodTypeService implements IFoodTypeService {
    //调用Dao
    //private IFoodTypeDao foodTypeDao = new FoodTypeDao();
    //对象的创建，不能写死，
    //用工厂创建对象
    private IFoodTypeDao foodTypeDao = BeanFactory.getInstance("foodtypeDao", IFoodTypeDao.class);


    @Override
    public void add(FoodType foodType) {
        foodTypeDao.add(foodType);
    }

    @Override
    public void delete(int id) {
        foodTypeDao.delete(id);
    }

    @Override
    public void update(FoodType foodType) {
        foodTypeDao.update(foodType);
    }

    @Override
    public List<FoodType> query() {
        return foodTypeDao.query();
    }

    @Override
    public FoodType findById(int id) {
        return foodTypeDao.findById(id);
    }

    @Override
    public List<FoodType> query(String keyword) {
        return foodTypeDao.query(keyword);
    }

    @Override
    public Integer getFirstType() {
        return foodTypeDao.getFirstType();
    }
}
