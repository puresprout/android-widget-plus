package com.purestation.widgetplus.serverconfig;

public class ServerConfig {

    private String name;
    private String url;

    public ServerConfig(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name + " (" + url + ")";
    }

}
