package org.apache.realtime.zeus.common.options;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.GlobalConfiguration;
import org.apache.realtime.zeus.common.enums.ClusterMode;
import org.apache.realtime.zeus.common.utils.PropertiesUtils;

@Data
@ToString @EqualsAndHashCode
@Setter @Getter
public class Options {
    @OptionRequired(description = "job type: etl/sql/ml")
    private String jobType;

    @OptionRequired(description = "running mode")
    private String mode = ClusterMode.local.name();

    @OptionRequired(description = "job config")
    private String job;

    @OptionRequired(description = "job name")
    private String jobName;

    @OptionRequired(description = "flink env properties")
    private String confPropties = "{}";

    @OptionRequired(description = "sql ext jar, eg udf jar")
    private String addJar;

    @OptionRequired(description = "flink configuration directory")
    private String flinkDir;

    private Configuration configuration = null;

    public Configuration loadFlinkConfiguration() {
        if (null == configuration) {
            Configuration dynamicConf = Configuration.fromMap(PropertiesUtils.conf2Map(confPropties));
            configuration = StringUtils.isEmpty(flinkDir) ?
                    new Configuration(dynamicConf)
                    : GlobalConfiguration.loadConfiguration(flinkDir, dynamicConf);
            // TODO: add options
        }

        return configuration;
    }
}
