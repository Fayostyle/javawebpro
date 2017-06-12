package cn.fayostyle.dao;

/**2. 菜系模块 接口设计
 * Created by HuangPan on 2017/5/28.
 */

import cn.fayostyle.entity.FoodType;

import java.util.List;

/**********************************      V1.0版本        ****************/

public interface IFoodTypeDao {
//    /**
//     * 添加
//     */
//    void save(FoodType foodType);
//
//    /**
//     * 更新
//     */
//    void update(FoodType foodType);
//
//    /**
//     * 删除
//     *
//     */
//    void delete(int id);
//
//    /**
//     *  查询全部
//     */
//    List<FoodType> getAll();
//
//    /**
//     * 根据主键查询
//     */
//    FoodType findById(int id);
//
//    /**
//     * 根据菜系名称查询
//     */
//    List<FoodType> getAll(String typeName);
//

    /*********************************  V2.0版本 ******************/

    void add(FoodType foodType);
    void delete(int id);
    void update(FoodType foodType);
    List<FoodType> query();
    FoodType findById(int id);
    List<FoodType> query(String keyword);
    Integer getFirstType();





}

