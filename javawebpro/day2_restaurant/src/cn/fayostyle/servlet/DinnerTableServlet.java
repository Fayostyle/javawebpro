package cn.fayostyle.servlet;

import cn.fayostyle.entity.DinnerTable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by HuangPan on 2017/6/13.
 */
public class DinnerTableServlet extends BaseServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init();
        List<DinnerTable> list = dinnerTableService.query();
        config.getServletContext().setAttribute("table", list);
    }

    private Object search(HttpServletRequest request, HttpServletResponse response) {
        Object uri = null;
        String keyword = request.getParameter("keyword");
        if(keyword != null && !"".equals(keyword.trim())) {
            List<DinnerTable> list = dinnerTableService.query(keyword);
            request.setAttribute("table", list);
            uri = request.getRequestDispatcher("/sys/");
        }
        return uri;
    }
}
