package cn.fayostyle.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HuangPan on 2017/5/25.
 */
public class FileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("upload".equals(method)) {
            upload(request, response);
        } else if ("downList".equals(method)) {
            downList(request, response);
        } else if ("down".equals(method)) {
            down(request, response);
        }
    }

    /**
     * 进入下载列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void downList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**实现思路：现货区upload目录下所有文件的文件名，再保存；跳转到down.jsp显示**/

        //1 初始化map集合Map<包含唯一标记的文件名，简短文件名>
        Map<String, String> fileNames = new HashMap<String, String>();

        //2 获取上传目录， 及其目录下的所有文件名
        String basePath = getServletContext().getRealPath("/upload");

        //目录
        File file = new File(basePath);

        //目录下，所有文件名
        String[] list = file.list();

        //遍历，封装
        if(list != null && list.length > 0) {
            for(int i=0; i<list.length; i++) {
                String fileName = list[i];
                String shortName = fileName.substring(fileName.lastIndexOf("#")+1);

                //封装
                fileNames.put(fileName, shortName);
            }
        }

        //保存到request域
        request.setAttribute("fileNames", fileNames);
        //转发
        request.getRequestDispatcher("/downlist.jsp").forward(request, response);

    }

    /**
     * c处理下载
     * @param request
     * @param response
     */
    private void down(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //获取用户下载的文件名称（url地址后追加数据，get)
        String fileName = request.getParameter("fileName");
        fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");

        //先获取上传目录路径
        String basePath = getServletContext().getRealPath("/upload");

        //获取一个文件流
        InputStream in = new FileInputStream(new File(basePath, fileName));

        //如果文件是中文名，需要进行url编码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //设置下载的响应头
        response.setHeader("content-disposition","attachment;fileName=" + fileName);

        //获取response字节流
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }

        //关闭
        out.close();
        in.close();
    }


    /**
     * 上传
     * @param request
     * @param response
     */
    private void upload(HttpServletRequest request, HttpServletResponse response) {
        //创建工厂对象
        FileItemFactory factory = new DiskFileItemFactory();

        //文件上传核心工具类
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(10*1024*1024);
        upload.setSizeMax(50*1024*1024);
        upload.setHeaderEncoding("UTF-8");

        //判断是否为包含文件的表单项
        if(upload.isMultipartContent(request)) {
            try {
                List<FileItem> list = upload.parseRequest(request);
                for(FileItem item : list) {
                    if(item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        System.out.println(value);
                    } else {
                        /**********文件上传*********/
                        //文件名
                        String name = item.getName();
                        /*****处理文件重名问题********/
                        //a1 先得到唯一标记
                        String id = UUID.randomUUID().toString();
                        //a2 拼接文件名
                        name = id + "#" + name;

                        //b 得到上传目录
                        String basePath = getServletContext().getRealPath("/upload");
                        // c 创建要上传的文件对象
                        File file = new File(basePath, name);
                        //d 上传
                        item.write(file);
                        item.delete(); //删除组件运行时产生的临时文件
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
