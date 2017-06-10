package cn.fayostyle.dao.impl;

import cn.fayostyle.dao.IFoodDao;
import cn.fayostyle.entity.Food;
import cn.fayostyle.utils.Condition;
import cn.fayostyle.utils.JdbcUtils;
import cn.fayostyle.utils.PageBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangPan on 2017/6/10.
 */
public class FoodDao implements IFoodDao {
    @Override
    public void getAll(PageBean<Food> pb) {
        //获取条件对象
        Condition condition = pb.getCondition();
        //条件之类别id
        int typeId = condition.getFoodTypeId();
        //条件之菜品名称
        String foodName = condition.getFoodName();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append(" f.id,");
        sb.append(" f.foodName,");
        sb.append(" f.price,");
        sb.append(" f.mprice,");
        sb.append(" f.remark,");
        sb.append(" f.img");
        sb.append(" f.foodType_id,");
        sb.append(" t.typeName");
        sb.append(" from");
        sb.append(" food f,");
        sb.append(" foodtype t");
        sb.append(" where 1=1");
        sb.append(" and f.foodType_id = t.id");
        //存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();

        /**拼接查询条件**/
        //类别id条件
        if(typeId > 0) {
            sb.append(" and f.foodType = ?");
            list.add(typeId); //组装条件值
        }

        if(foodName != null && !"".equals(foodName.trim())) {
            sb.append(" and f.foodName like ?");
            list.add(foodName); //组装条件值
        }

        /********* 分页条件 ******************/
        sb.append(" limit ?,?");

        /** 判断： 当前页 < 1 ， 设置当前页为1， 当前页 > 最大页数， 设置当前页为总页数**/
        //先查询总记录数
        int totalCount = getTotalCount(pb);    //调用这个类自身的方法， 就在下面
        pb.setTotalCount(totalCount);

        if(pb.getCurrentPage() < 1) {
            pb.setCurrentPage(1);
        } else if (pb.getCurrentPage() > pb.getTotalPage()) {
            pb.setCurrentPage(pb.getTotalPage());
        }

        //起始行  不包括本行
        int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
        int count = pb.getPageCount();

        list.add(index);           // 组装条件值 - 起始行
        list.add(count);           // 组装条件值 - 查询返回的行

        //按条件分页查询
        try {
            List<Food> pageData = JdbcUtils.getQueryRunner().query(sb.toString(),
                    new BeanListHandler<Food>(Food.class), list.toArray());

            pb.setPageData(pageData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Food> pb) {
        //获取条件对象
        Condition condition = pb.getCondition();
        //条件之类别id
        int typeId = condition.getFoodTypeId();
        //条件之菜品名称
        String foodName = condition.getFoodName();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append(" count(*)");
        sb.append(" from");
        sb.append(" food f,");
        sb.append(" foodType t");
        sb.append(" where 1=1");
        sb.append(" and f.foodType_id=t.id");

        //存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();

        if(typeId > 0) {
            sb.append(" and f.foodType_id=?");
            list.add(typeId);
        }
        if(foodName != null && !"".equals(foodName.trim())) {
            sb.append(" and f.foodName like ?");
            list.add(foodName);
        }

        try {
            Long num = JdbcUtils.getQueryRunner().query(sb.toString(), new ScalarHandler<Long>(), list.toArray());
            return num.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findById(int id) {
        String sql = "select f.id, f.foodName,f.price,f.mprice,f.remark,f.img," +
                "f.foodType_id,t.typeName from food f, foodtype t where 1=1 and " +
                "f.foodType_id = t.id";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
