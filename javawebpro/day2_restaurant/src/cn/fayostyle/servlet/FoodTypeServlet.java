package cn.fayostyle.servlet;

import cn.fayostyle.entity.FoodType;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 4. 菜系管理servlet开发
 * Created by HuangPan on 2017/5/31.
 */
public class FoodTypeServlet extends BaseServlet {
/**
    private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodtypeService", IFoodTypeService.class);
    private Object uri;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取操作类型
        String method = request.getParameter("method");
        if("addFoodType".equals(method)) {
            addFoodType(request, response);
        } else if("list".equals(method)) {
            list(request, response);
        } else if("viewUpdate".equals(method)) {
            viewUpdate(request, response);
        } else if("delete".equals(method)) {
            delete(request, response);
        } else if("update".equals(method)) {
            update(request, response);
        }
    }
    **/

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List<FoodType> list = foodTypeService.query();
        config.getServletContext().setAttribute("foodtype", list);
    }
    public Object update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Object uri = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("foodTypeName");
        FoodType foodType = new FoodType();
        foodType.setId(id);
        foodType.setTypeName(name);

        foodTypeService.update(foodType);
        uri = "/foodType?method=list";
        return uri;
    }
    public Object delete(HttpServletRequest request, HttpServletResponse response) {
        Object uri = null;
        String id = request.getParameter("id");
        foodTypeService.delete(Integer.parseInt(id));
        uri = "/foodType?method=list";
        return uri;
    }
    public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object uri = null;
        String id = request.getParameter("id");
        FoodType ft = foodTypeService.findById(Integer.parseInt(id));
        request.setAttribute("foodType", ft);
        uri = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
        return uri;
    }

    public Object list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object uri = null;
        List<FoodType> list = foodTypeService.query();
        request.setAttribute("listFoodType", list);
        uri = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");
        return uri;
    }

    public Object addFoodType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object uri = null;
        String foodTypeName = request.getParameter("foodTypeName");
        FoodType ft = new FoodType();
        ft.setTypeName(foodTypeName);
        foodTypeService.add(ft);
        uri = request.getRequestDispatcher("/foodType?method=list");
        return uri;
    }

    public void search(HttpServletRequest request, HttpServletResponse response) {
        Object uri = null;
        String keyword = request.getParameter("keyword");
        if(keyword != null && !"".equals(keyword.trim())) {
            List<FoodType> list = foodTypeService.query(keyword);
            request.setAttribute("list", list);
            uri = request.getRequestDispatcher("/foodType?method=list");
        }
    }
    
}
