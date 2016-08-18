package com.purestation.widgetplus.serverconfig;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ServerConfigSelectionView extends LinearLayout {

    public interface OnServerSelectedListener {
        void onServerSelected(String url);
    }

    private Context mContext;
    private ServerConfigManager mManager;

    private OnServerSelectedListener mListener;

    public ServerConfigSelectionView(Context context, ServerConfigManager manager) {
        super(context);

        mContext = context;
        mManager = manager;

        setOrientation(VERTICAL);

        buildUI();
    }

    private void buildUI() {
        String targetServer = mManager.getTargetServer();

        final RadioGroup radioGroup = new RadioGroup(mContext);
        addView(radioGroup, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        for (final ServerConfig serverConfig : mManager.getServerConfigList()) {
            RadioButton radioButton = new RadioButton(mContext);

            radioButton.setText(serverConfig.toString());
            radioButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mManager.setTargetServer(serverConfig.getUrl());

                    radioGroup.check(v.getId());

                    if (mListener != null) {
                        mListener.onServerSelected(serverConfig.getUrl());
                    }
                }
            });

            radioGroup.addView(radioButton, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            if (targetServer.equals(serverConfig.getUrl())) {
                radioGroup.check(radioButton.getId());
            }
        }
    }

    public void setOnServerSelectedListener(OnServerSelectedListener listener) {
        this.mListener = listener;
    }

}
