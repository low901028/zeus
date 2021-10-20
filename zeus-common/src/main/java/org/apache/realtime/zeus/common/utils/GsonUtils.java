package org.apache.realtime.zeus.common.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>Gson工具类，用于对json的序列化及反序列化，及解决int类型在map中被转换成double类型问题
 *
 * @author tudou
 */
public class GsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(GsonUtils.class);
    public static Gson GSON = getGson();
    public static Type gsonMapTypeToken = new TypeToken<HashMap<String, Object>>() {}.getType();

    private static Gson getGson() {
        GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        return setTypeAdapter(GSON);
    }

    @SuppressWarnings("all")
    static public Gson setTypeAdapter(Gson gson) {
        try {
            Field factories = Gson.class.getDeclaredField("factories");
            factories.setAccessible(true);
            Object o = factories.get(gson);
            Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
            for (Class c : declaredClasses) {
                if ("java.util.Collections$UnmodifiableList".equals(c.getName())) {
                    Field listField = c.getDeclaredField("list");
                    listField.setAccessible(true);
                    List<TypeAdapterFactory> list = (List<TypeAdapterFactory>) listField.get(o);
                    int i = list.indexOf(ObjectTypeAdapter.FACTORY);
                    list.set(
                            i,
                            new TypeAdapterFactory() {
                                @Override
                                public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                                    if (type.getRawType() == Object.class) {
                                        return new TypeAdapter() {
                                            @Override
                                            public Object read(JsonReader in) throws IOException {
                                                JsonToken token = in.peek();
                                                // 判断字符串的实际类型
                                                switch (token) {
                                                    case BEGIN_ARRAY:
                                                        List<Object> list = new ArrayList<>();
                                                        in.beginArray();
                                                        while (in.hasNext()) {
                                                            list.add(read(in));
                                                        }
                                                        in.endArray();
                                                        return list;

                                                    case BEGIN_OBJECT:
                                                        Map<String, Object> map =
                                                                new LinkedTreeMap<>();
                                                        in.beginObject();
                                                        while (in.hasNext()) {
                                                            map.put(in.nextName(), read(in));
                                                        }
                                                        in.endObject();
                                                        return map;
                                                    case STRING:
                                                        return in.nextString();
                                                    case NUMBER:
                                                        String s = in.nextString();
                                                        if (s.contains(".")) {
                                                            return Double.valueOf(s);
                                                        } else {
                                                            try {
                                                                return Integer.valueOf(s);
                                                            } catch (Exception e) {
                                                                try {
                                                                    return Long.valueOf(s);
                                                                } catch (Exception e1) {
                                                                    return new BigInteger(s);
                                                                }
                                                            }
                                                        }
                                                    case BOOLEAN:
                                                        return in.nextBoolean();
                                                    case NULL:
                                                        in.nextNull();
                                                        return null;
                                                    default:
                                                        throw new IllegalStateException();
                                                }
                                            }

                                            @Override
                                            public void write(JsonWriter out, Object value)
                                                    throws IOException {
                                                if (value == null) {
                                                    out.nullValue();
                                                    return;
                                                }
                                                //noinspection unchecked
                                                TypeAdapter<Object> typeAdapter =
                                                        gson.getAdapter(
                                                                (Class<Object>) value.getClass());
                                                if (typeAdapter instanceof ObjectTypeAdapter) {
                                                    out.beginObject();
                                                    out.endObject();
                                                    return;
                                                }
                                                typeAdapter.write(out, value);
                                            }
                                        };
                                    }
                                    return null;
                                }
                            });
                    break;
                }
            }
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getErrorMessage(e));
        }
        return gson;
    }
}
