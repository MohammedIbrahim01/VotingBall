package com.example.android.crystalball.OptionPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.crystalball.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterForCustomListViews extends BaseAdapter {

    List<OptionEntry> OptionsListInAdapter = new ArrayList<>();

    public void setOptionsListInAdapter(List<OptionEntry> optionsListInAdapter) {
        OptionsListInAdapter = optionsListInAdapter;
    }

    public List<OptionEntry> getOptionsListInAdapter() {
        return OptionsListInAdapter;
    }


    @Override
    public int getCount() {
        return OptionsListInAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.one_option_layout, parent, false);
        }
        //CustomObjectForOneOptionItems current = getItem(position);

        OptionEntry current = OptionsListInAdapter.get(position);

        TextView oneOption = listItemView.findViewById(R.id.oneOption);
        oneOption.setText(current.getDescription());
        return listItemView;
    }
}
