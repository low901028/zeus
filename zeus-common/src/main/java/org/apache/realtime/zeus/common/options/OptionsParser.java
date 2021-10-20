package org.apache.realtime.zeus.common.options;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.realtime.zeus.common.utils.MapUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class OptionsParser {
    private static final String OPTION_JOB = "job";

    private final org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();

    private final DefaultParser parser = new DefaultParser();

    private final Options properties = new Options();

    public OptionsParser(String[] args) throws Exception {
        Class cla = properties.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            OptionRequired optionRequired = field.getAnnotation(OptionRequired.class);
            if (optionRequired != null) {
                options.addOption(name, optionRequired.hasArg(), optionRequired.description());
            }
        }
        CommandLine cl = parser.parse(options, args);

        for (Field field : fields) {
            String name = field.getName();
            String value = cl.getOptionValue(name);
            OptionRequired optionRequired = field.getAnnotation(OptionRequired.class);

            if (optionRequired != null) {
                if (optionRequired.required() && StringUtils.isBlank(value)) {
                    throw new RuntimeException(String.format("parameters of %s is required", name));
                }
            }

            if (StringUtils.isNotBlank(value)) {
                field.setAccessible(true);
                field.set(properties, value);
            }
        }
    }

    public List<String> getProgramExeArgList() throws Exception {
        Map<String, Object> mapConf = MapUtils.objectToMap(properties);
        List<String> args = new ArrayList<>();
        for (Map.Entry<String, Object> one : mapConf.entrySet()) {
            String key = one.getKey();
            Object value = one.getValue();
            if (value == null) {
                continue;
            } else if (OPTION_JOB.equalsIgnoreCase(key)) {
                File file = new File(value.toString());
                try (FileInputStream in = new FileInputStream(file)) {
                    byte[] fileContent = new byte[(int) file.length()];
                    in.read(fileContent);
                    value = new String(fileContent, StandardCharsets.UTF_8);
                }
            }
            args.add("-" + key);
            args.add(value.toString());
        }
        return args;
    }

    public Options getOptions() {
        return properties;
    }
}
