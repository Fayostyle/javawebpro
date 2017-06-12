package cn.fayostyle.servlet;

import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.entity.Food;
import cn.fayostyle.entity.FoodType;
import cn.fayostyle.utils.Condition;
import cn.fayostyle.utils.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HuangPan on 2017/6/11.
 */
public class FoodServlet extends BaseServlet {
    /**
     * 1 进入主页，显示菜品以及菜系
     *
     */
    public Object foodDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //获取餐桌id， 根据id查询，再把查询结果保存到session（生成订单用）
        //只需要执行 一次 即可：先从session获取看有没有餐桌对象，如果没有，就执行根据主键查询
        //如果session中已经有餐桌对象，就不执行主键查询
        Object obj = session.getAttribute("dinnerTable");

        //判断
        if(obj == null) {
            //只在第一次执行的时候，查询餐桌对象
            String tableId = request.getParameter("tableId");
            DinnerTable dt = dinnerTableService.findById(Integer.parseInt(tableId));

            //保存到session
            session.setAttribute("dinnerTable", dt);
        }


        // 1.2 查询所有的“菜品信息”，保存
        PageBean<Food> pb = new PageBean<Food>();
        //分页参数； 获取当前页参数
        String curPage = request.getParameter("currentPage");
        //判断
        if(curPage == null || "".equals(curPage.trim())) {
            pb.setCurrentPage(1);
        } else {
            pb.setCurrentPage(Integer.parseInt(curPage));
        }

        //条件对象
        Condition condition = new Condition();
        // 分页参数： 菜系id
        String foodTypeId = request.getParameter("foodTypeId");
        if(foodTypeId != null) {  /** 如果类别为null，不作为条件，就查询全部 **/
            condition.setFoodTypeId(Integer.parseInt(foodTypeId));
        }
        // 分页参数: 菜名称
        String foodName = request.getParameter("foodName");
        if(foodName != null && !"".equals(foodName)) {
            condition.setFoodName(foodName);
        }
        //设置条件对象到pg中
        pb.setCondition(condition);
        //执行查询
        foodService.getAll(pb);
        //FoodDao中的查询返回为void， 这里需要保存查询后的pb对象
        request.setAttribute("pb", pb);

        // 1.3 查询所有的“菜系信息”，保存
        List<FoodType> listFoodType = foodTypeService.query();
        request.setAttribute("listFoodType", listFoodType);

        //挑战 转发
        return request.getRequestDispatcher("/app/caidan.jsp");


    }
}
