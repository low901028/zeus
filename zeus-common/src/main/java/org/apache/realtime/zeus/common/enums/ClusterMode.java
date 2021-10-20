package org.apache.realtime.zeus.common.enums;

import org.apache.commons.lang.StringUtils;

/**
 * cluster mode
 */
public enum ClusterMode {
    local(0, "local"),
    standalone(1, "standalone"),
    yarnSession(2, "yarn-session"),
    yarnPreJob(3, "yarn-pre-job"),
    yarnApplication(4, "yarn-application"),
    kubernetesSession(5, "kubernetes-session"),
    kubernetesPreJob(6, "kubernetes-pre-job"),
    kubernetesApplication(7, "kubernetes-application");

    private int type;
    private String name;

    ClusterMode(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static ClusterMode getByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("ClusterMode name cannot be null or empty");
        }

        switch (name) {
            case "standalone":
                return standalone;
            case "yarn-session":
                return yarnSession;
            case "yarn-pre-job":
                return yarnPreJob;
            case "yarn-application":
                return yarnApplication;
            case "kubernetes-session":
                return kubernetesSession;
            case "kubernetes-pre-job":
                return kubernetesPreJob;
            case "kubernetes-application":
                return kubernetesApplication;
            default:
                return local;
        }
    }
}
