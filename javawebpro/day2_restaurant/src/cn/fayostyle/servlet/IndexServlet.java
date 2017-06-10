package cn.fayostyle.servlet;

import cn.fayostyle.entity.DinnerTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by HuangPan on 2017/6/9.
 */
public class IndexServlet extends BaseServlet {

    /**private IDinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if((method == null) || "listTable".equals(method)) {
            listTable(request, response);
        }
    }
     **/

    private Object listTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        List<DinnerTable> list = dinnerTableService.findNoUseTable();
        request.setAttribute("listDinnerTable", list);
        uri = request.getRequestDispatcher("/app/index.jsp");
        return uri;
        //跳转
        //WebUtils.goTo(request, response, uri);
    }
}
