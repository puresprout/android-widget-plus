package com.purestation.widgetplus.serverconfig;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
            radioButton.setTag(serverConfig);
            radioGroup.addView(radioButton, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            if (targetServer.equals(serverConfig.getUrl())) {
                radioGroup.check(radioButton.getId());
            }
        }

        final RadioButton customRadioButton = new RadioButton(mContext);
        customRadioButton.setText("사용자 정의");
        radioGroup.addView(customRadioButton, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        final EditText editText = new EditText(mContext);
        editText.setHint("https://naver.com");
        addView(editText, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            radioGroup.check(customRadioButton.getId());
            editText.setText(targetServer);
        } else {
            editText.setEnabled(false);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == customRadioButton.getId()) {
                    editText.setEnabled(true);
                } else {
                    editText.setEnabled(false);
                }
            }
        });

        Button okButton = new Button(mContext);
        okButton.setText("확인");
        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url;
                if (radioGroup.getCheckedRadioButtonId() == customRadioButton.getId()) {
                    url = editText.getText().toString();
                } else {
                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    url = ((ServerConfig) radioButton.getTag()).getUrl();
                }
                mManager.setTargetServer(url);

                if (mListener != null) {
                    mListener.onServerSelected(url);
                }
            }
        });
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        addView(okButton, layoutParams);
    }

    public void setOnServerSelectedListener(OnServerSelectedListener listener) {
        this.mListener = listener;
    }

}
