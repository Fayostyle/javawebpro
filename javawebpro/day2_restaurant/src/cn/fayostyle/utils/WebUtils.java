package cn.fayostyle.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by HuangPan on 2017/6/9.
 */
public class WebUtils {
    /**
     * 跳转的通用方法
     */
    public static void goTo(HttpServletRequest request, HttpServletResponse response, Object uri) throws ServletException, IOException {
        if(uri instanceof RequestDispatcher) {
            ((RequestDispatcher) uri).forward(request, response);
        } else if (uri instanceof String) {
            response.sendRedirect(request.getContextPath() + uri);
        } else if(uri == null) {
            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
        }
    }
}
