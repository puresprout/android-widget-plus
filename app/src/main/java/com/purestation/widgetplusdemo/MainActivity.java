package com.purestation.widgetplusdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.purestation.widgetplus.image.ProgressImageView;
import com.purestation.widgetplusdemo.adapter.ExperimentArrayAdapter;
import com.purestation.widgetplusdemo.adapter.ExperimentListViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ExperimentListViewItem> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES | PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            finish();
        }

        // 이 앱이 타겟팅하는 최소 버전임
        int appMinVersion = packageInfo.applicationInfo.targetSdkVersion;

        activities = new ArrayList<>();
        for (ActivityInfo activityInfo : packageInfo.activities) {
            if (activityInfo.metaData == null || !activityInfo.metaData.getBoolean("isLaunchableActivity", false)) {
                continue;
            }

            String label = getString(activityInfo.labelRes);
            int minVersion = activityInfo.metaData.getInt("minVersion", appMinVersion);

            activities.add(new Experiment(label, minVersion, makeIntent(activityInfo)));
        }

        ListView listView = (ListView) findViewById(R.id.demo_list);
        ExperimentArrayAdapter adapter = new ExperimentArrayAdapter(this, R.layout.list_item, activities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Experiment experiment = (Experiment) activities.get(position);
                startActivity(experiment.intent);
            }
        });
    }

    private Intent makeIntent(ActivityInfo activityInfo) {
        Intent intent = new Intent();
        intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
        return intent;
    }

    private class Experiment implements ExperimentListViewItem {
        public final String title;
        public final int minVersion;
        public final Intent intent;

        public Experiment(String title, int minVersion, Intent intent) {
            this.title = title;
            this.minVersion = minVersion;
            this.intent = intent;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public boolean isEnabled() {
            return Build.VERSION.SDK_INT >= minVersion;
        }

        @Override
        public String getDisabledText() {
            String disabledText = getString(R.string.list_item_disabled);
            return String.format(disabledText, minVersion);
        }
    }

}
