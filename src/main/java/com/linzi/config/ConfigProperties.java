package com.linzi.config;


import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * 
 *@类描述：配置属性
 *@author 陈金虎 2017年1月17日 下午6:14:21
 *@注意：本内容仅限于杭州霖梓网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ConfigProperties {

    private static Properties config     = new Properties();

    private String            configPath = null;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public static void init(String configPath) {
        try {
            config.load(ConfigProperties.class.getClassLoader().getResourceAsStream(configPath));
        } catch (Exception e) {
            System.out.println("ConfigProperties->init 没有配制variable.properties 环境变量，使用系统默认环境变量...");
        }
    }

    /**
     * @return
     */
    public static Properties getConfig() {
        return config;
    }

    /**
     * Gets the.
     * 
     * @param key the key
     * @return the string
     */
    public static String get(String key) {
        return config.getProperty(key);
    }

    /**
     * Gets the.
     * 
     * @param key the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String get(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }
    
    public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
}
