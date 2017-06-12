package cn.fayostyle.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HuangPan on 2017/6/12.
 */
public class FilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=UTF-8");

        HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(
                request.getClass().getClassLoader(),
                new Class[]{HttpServletRequest.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;
                        String methodName = method .getName();
                        if("getParameter".equals(methodName)) {
                            String value = request.getParameter(args[0].toString());
                            String methodSubmit = request.getMethod();
                            if("GET".equals(methodSubmit)) {
                                if(value != null && !"".equals(value.trim())) {
                                    value = new String(value.getBytes("ISO8859-1"),"UTF-8");
                                }
                            }
                            return value;
                        }
                        else {
                            returnValue = method.invoke(request, args);
                        }
                        return returnValue;
                    }
                }
        );

        filterChain.doFilter(proxy, response);
    }

    @Override
    public void destroy() {

    }
}
