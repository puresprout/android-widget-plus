package com.purestation.widgetplusdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.purestation.widgetplusdemo.R;

import java.util.List;

public class ExperimentArrayAdapter extends ArrayAdapter<ExperimentListViewItem> {

    private final LayoutInflater layoutInflater;

    public ExperimentArrayAdapter(Context context, int resource, List<ExperimentListViewItem> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        // 데모용 코드라서 뷰 재활용을 안함.

        TextView textView = (TextView) convertView.findViewById(R.id.list_item_text);
        textView.setText(getItem(position).getTitle());

        TextView disabledTextView = (TextView) convertView.findViewById(R.id.list_item_disabled_text);
        disabledTextView.setText(getItem(position).getDisabledText());

        if (isEnabled(position)) {
            disabledTextView.setVisibility(View.INVISIBLE);
            textView.setTextColor(Color.BLACK);
        } else {
            disabledTextView.setVisibility(View.VISIBLE);
            textView.setTextColor(Color.GRAY);
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

}
