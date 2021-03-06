package com.zmcsoft.apsp.client.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhouhao
 * @since
 */
@Slf4j
@SuppressWarnings("all")
public class ApplicationConfig {

    static Map<String, Object> config = new HashMap<>();

    static {
        try {
            config = (Map) Yaml.load(new File("./conf/global.yml"));
            log.info("load config:\n{}", JSON.toJSONString(config, SerializerFeature.PrettyFormat));
        } catch (FileNotFoundException e) {
            log.error("load config error", e);
        }
    }

    public static String getConfig(String name, String defaultValue) {
        return Optional
                .ofNullable(Optional
                        .ofNullable(config.get(name))
                        .map(String::valueOf)
                        .orElse(null))
                .orElseGet(() -> {
                    try {
                        String result = BeanUtils.getProperty(config, name);
                        return result == null ? defaultValue : result;
                    } catch (Exception e) {
                        log.warn("get config {} error:{}", name, e.getMessage());
                    }
                    return defaultValue;
                });
    }
}
