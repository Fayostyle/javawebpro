package cn.fayostyle.factory;

import java.util.ResourceBundle;



/**工厂 创建dao或service实例
 * Created by HuangPan on 2017/5/31.
 */
public class BeanFactory {
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle("instance");

    }

    /**
     * 根据指定的key，读取配置文件获取类的全路径； 创建对象
     */
    public static<T> T getInstance(String key, Class<T> clazz) {
        String className = bundle.getString(key);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
