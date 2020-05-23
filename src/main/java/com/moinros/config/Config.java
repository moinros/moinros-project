package com.moinros.config;

import com.moinros.project.model.pojo.system.Access;
import com.moinros.project.tool.email.sub.EmailConfig;
import com.moinros.project.tool.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 类注释: 项目自定义全局配置信息
 *
 * @Title: Config
 * @Author moinros
 * @Blog https://www.moinros.com
 * @Date 2019年10月30日 上午2:32:25
 * @Version 1.0
 */
public class Config {

    private Config() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    /**
     * UTF8 : UTF-8编码格式
     */
    public static final String UTF8 = "UTF-8";

    public static Properties pro = null;

    public static Properties getConfig() {
        return pro;
    }

    public static void init() {
        EmailConfig conf = EmailConfig.getConfig();
        if (isWindows()) {
            conf.setConfigPath("D:/java/config/moinros-config.properties");
        } else {
            conf.setConfigPath("/usr/local/java/moinros-config.properties");
        }
        conf.init();
    }

    /**
     * 允许访问后台管理的IP地址
     */
    public static Access[] ACCESS = null;
//    public static String[] ACCESS = {
//            "127.0.0.1",
//            "192.168.1.1",
//            "192.168.1.2",
//            "192.168.1.3",
//            "192.168.1.4",
//            "192.168.1.5",
//            "192.168.1.6",
//            "192.168.1.7",
//            "192.168.1.8",
//            "192.168.1.9",
//            "61.188.138.185",
//    };

    public static String get(String key) {
        return pro.getProperty(key);
    }

    static {
        LOG.info("============================== 项目启动！开始读取配置文件！ ==============================");
        pro = new Properties();
        File f;
        InputStream is = null;

        if (SystemUtil.isWindows()) {
            f = new File("D:/java/config/datasource.properties");
        } else {
            f = new File("/usr/local/java/datasource.properties");
        }
        try {
            is = new FileInputStream(f);
            pro.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            LOG.error("============================== ERROR ==============================");
            LOG.error(">> 读取配置文件出现异常！！\\t程序结束！！");
            LOG.error(e.toString());
            LOG.error("============================== ERROR ==============================");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOG.info("============================== 项目启动！配置文件读取完成！ ==============================");
    }

    public static boolean isWindows() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            return true;
        }
        return false;
    }
}
