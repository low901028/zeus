package org.apache.realtime.zeus.common.utils;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.realtime.zeus.common.exceptions.ZeusFlinkRuntimeException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    static public Properties parseConfig(String strconf) {
        if (StringUtils.isEmpty(strconf)){
            return  new Properties();
        }
        try {
            strconf = URLDecoder.decode(strconf, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new ZeusFlinkRuntimeException(e);
        }
        return GsonUtils.GSON.fromJson(strconf, Properties.class);
    }

    static public Map<String, String> conf2Map(String strconf) {
        if (StringUtils.isEmpty(strconf)) {
            return  new HashMap<>();
        }

        try {
            strconf = URLDecoder.decode(strconf, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new ZeusFlinkRuntimeException(e);
        }
        return GsonUtils.GSON.fromJson(strconf, Map.class);
    }

    static public Properties propertiesTrim(final Properties props) {
        Properties resProps = new Properties();
        props.forEach((k,v)-> {
            resProps.put(k.toString().trim(), v.toString().trim());
        });

        return resProps;
    }
}
