package com.example.android.crystalball.OptionPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.crystalball.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterForCustomListViews extends ArrayAdapter<CustomObjectForOneOptionItems> {

    List<CustomObjectForOneOptionItems> mohamedList = new ArrayList<>();

    public List<CustomObjectForOneOptionItems> getMohamedList() {
        return mohamedList;
    }

    public AdapterForCustomListViews(@NonNull Context context, int resource, @NonNull List<CustomObjectForOneOptionItems> objects) {
        super(context, 0, objects);
        mohamedList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.one_option_layout, parent, false);
        }
        CustomObjectForOneOptionItems current = getItem(position);
        TextView oneOptionId = listItemView.findViewById(R.id.oneOptionId);
        oneOptionId.setText(String.valueOf(current.getOneOptionId()));
        TextView oneOption = listItemView.findViewById(R.id.oneOption);
        oneOption.setText(current.getOneOption());
        return listItemView;
    }
}
