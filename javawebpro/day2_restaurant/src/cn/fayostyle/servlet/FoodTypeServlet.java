package cn.fayostyle.servlet;

import cn.fayostyle.entity.FoodType;
import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IFoodTypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 4. 菜系管理servlet开发
 * Created by HuangPan on 2017/5/31.
 */
public class FoodTypeServlet extends HttpServlet {

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

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("foodTypeName");
            FoodType foodType = new FoodType();
            foodType.setId(id);
            foodType.setTypeName(name);

            foodTypeService.update(foodType);

            uri = "/foodType?method=list";
        } catch (Exception e) {
            uri = "/error/error.jsp";
        }
        goTo(request, response, uri);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String id = request.getParameter("id");
            foodTypeService.delete(Integer.parseInt(id));
            uri = "/foodType?method=list";
        } catch (Exception e) {
            uri = "/error/error.jsp";
        }
        goTo(request, response, uri);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String id = request.getParameter("id");
            FoodType ft = foodTypeService.findById(Integer.parseInt(id));
            request.setAttribute("foodType", ft);
            uri = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
        } catch (Exception e) {
            uri = "/error/error.jsp";
        }
        goTo(request, response, uri);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<FoodType> list = foodTypeService.getAll();
            request.setAttribute("listFoodType", list);
            uri = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");
        } catch (Exception e) {
            uri = "/error/error.jsp";
        }
        goTo(request, response, uri);
    }

    private void addFoodType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String foodTypeName = request.getParameter("foodTypeName");
            FoodType ft = new FoodType();
            ft.setTypeName(foodTypeName);
            foodTypeService.save(ft);
            uri = request.getRequestDispatcher("/foodType?method=list");
        } catch (Exception e) {
            uri = "/error/error.jsp";
        }
        goTo(request, response, uri);
    }

    private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri) throws IOException, ServletException {
        if(uri instanceof RequestDispatcher) {
            ((RequestDispatcher) uri).forward(request, response);
        } else if(uri instanceof String) {
            response.sendRedirect(request.getContextPath() + uri);
        }
    }
}