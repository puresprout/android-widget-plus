package com.purestation.widgetplusdemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.purestation.widgetplus.serverconfig.ServerConfig;
import com.purestation.widgetplus.serverconfig.ServerConfigManager;
import com.purestation.widgetplus.serverconfig.ServerConfigSelectionView;

import java.util.ArrayList;
import java.util.List;

public class ServerConfigActivity extends AppCompatActivity {

    private ServerConfigManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_config);

        mManager = new ServerConfigManager(this, makeServerConfigList(), makeDefaultConfig());
    }

    public void onClickServerConfig(View view) {
        ServerConfigSelectionView serverConfigSelectionView = new ServerConfigSelectionView(this, mManager);
        final AlertDialog dialog = new AlertDialog.Builder(this).setMessage(R.string.select_server).setView(serverConfigSelectionView).show();
        serverConfigSelectionView.setOnServerSelectedListener(new ServerConfigSelectionView.OnServerSelectedListener() {
            @Override
            public void onServerSelected(String url) {
                Toast.makeText(ServerConfigActivity.this, url + " selected.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private List<ServerConfig> makeServerConfigList() {
        List<ServerConfig> list = new ArrayList();
        list.add(new ServerConfig("DEV", "http://dev-naver.com"));
        list.add(new ServerConfig("STAGE", "http://stage-naver.com"));
        list.add(new ServerConfig("REAL", "http://naver.com"));
        return list;
    }

    private ServerConfig makeDefaultConfig() {
        return new ServerConfig("REAL", "http://naver.com");
    }

}
