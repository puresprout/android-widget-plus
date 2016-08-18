package com.purestation.widgetplus.serverconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.List;

public class ServerConfigManager {

    private static final String PREF_NAME = "serverConfig";
    private static final String TARGET_SERVER = "targetServer";

    private List<ServerConfig> mList;
    private ServerConfig mDefaultConfig;
    private Context mCtx;

    private SharedPreferences sharedPref;

    public ServerConfigManager(Context ctx, List<ServerConfig> list, ServerConfig defaultConfig) {
        mCtx = ctx.getApplicationContext();
        mList = list;
        mDefaultConfig = defaultConfig;

        sharedPref = mCtx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<ServerConfig> getServerConfigList() {
        return mList;
    }

    public String getTargetServer() {
        String targetServer = getPrefTargetServer();

        if (TextUtils.isEmpty(targetServer)) {
            targetServer = mDefaultConfig.getUrl();
        }

        return targetServer;
    }

    public void setTargetServer(String targetServer) {
        setPrefTargetServer(targetServer);
    }

    // NOTICE package 접근자 사용. 다른 패키지에서 직접 호출못하도록.
    String getPrefTargetServer() {
        return sharedPref.getString(TARGET_SERVER, "");
    }

    void removePrefTargetServer() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(TARGET_SERVER);
        editor.apply();
    }

    void setPrefTargetServer(String targetServer) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TARGET_SERVER, targetServer);
        editor.apply();
    }

}
