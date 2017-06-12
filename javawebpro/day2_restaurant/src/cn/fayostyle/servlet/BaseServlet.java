package cn.fayostyle.servlet;

import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IDinnerTableService;
import cn.fayostyle.service.IFoodService;
import cn.fayostyle.service.IFoodTypeService;
import cn.fayostyle.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *  项目中通用的servlet， 希望所有的servlet都继承此类
 * Created by HuangPan on 2017/6/9.
 */
public class BaseServlet extends HttpServlet {

    protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    protected IDinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
    protected IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //（保存跳转的资源） 方法返回值
        Object returnValue = null;

        //获取操作类型
        String methodName = request.getParameter("method");

        try {
            //获取当前运行类的字节码
            Class clazz = this.getClass();
            //获取当前执行方法的Method类型
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            returnValue = method.invoke(this, request, response);
        } catch (Exception e) {
            returnValue = "/error/error.jsp";
        }

        //跳转
        WebUtils.goTo(request, response, returnValue);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
