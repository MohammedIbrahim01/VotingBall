package com.example.android.crystalball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.crystalball.OptionPackage.AdapterForCustomListViews;
import com.example.android.crystalball.OptionPackage.AppExecutor;
import com.example.android.crystalball.OptionPackage.CustomObjectForOneOptionItems;
import com.example.android.crystalball.OptionPackage.OptionDatabase;
import com.example.android.crystalball.OptionPackage.OptionEntry;

import java.util.ArrayList;
import java.util.List;

public class DropYourOptionsActivity extends AppCompatActivity {
    Button btnAdd;
    ListView optionListView;
    EditText enterYourOptionEditText;
    OptionDatabase database;
    List<OptionEntry> listOfOptionEntry = new ArrayList<>();
    TextView numberOfOptionTextView;
    AdapterForCustomListViews adapter;
    int numberOfOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_your_option);
        calling();
        handling();
        adapter = new AdapterForCustomListViews(getApplicationContext(), R.layout.one_option_layout);
        optionListView.setAdapter(adapter);
        retrieveOptions();
        handlingDeletion();
    }

    void handling() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOptionToDataBase();
                enterYourOptionEditText.setText("");
                retrieveOptions();
            }
        });
    }

    void retrieveOptions() {
        numberOfOptions = database.OptionDao().loadAllOptions().size();
        numberOfOptionTextView.setText("number of Options is " + numberOfOptions);

        listOfOptionEntry = database.OptionDao().loadAllOptions();

        adapter.setOptionsListInAdapter(listOfOptionEntry);
        adapter.notifyDataSetChanged();
    }

   /* void retrieveOptions() {
        numberOfOptions = database.OptionDao().loadAllOptions().size();
        //int numberOfOptions = database.OptionDao().loadAllOptions().lastIndexOf(listOfOptionEntry);
        numberOfOptionTextView.setText("number of Options is " + numberOfOptions);
        listOfOptionEntry.clear();
        for (int i = 0; i < numberOfOptions; i++) {
            listOfOptionEntry.add(new CustomObjectForOneOptionItems(database.OptionDao().loadAllOptions().
                    get(i).getId(), database.OptionDao().loadAllOptions().get(i).getDescription()));
        }
        adapter = new AdapterForCustomListViews(getApplicationContext(), R.layout.one_option_layout);
        adapter.setOptionsListInAdapter(listOfOptionEntry);

        optionListView.setAdapter(adapter);
    }*/


    void handlingDeletion() {

        optionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<OptionEntry> optionsListFromAdapter = adapter.getOptionsListInAdapter();
                deleteOptionFromDataBase(optionsListFromAdapter.get(position));
                retrieveOptions();
            }
        });
    }

    private void saveOptionToDataBase() {

        String option = enterYourOptionEditText.getText().toString();
        OptionEntry optionEntry = new OptionEntry(option);
        database.OptionDao().insertOption(optionEntry);

    }

    private void deleteOptionFromDataBase(final OptionEntry optionEntry) {

        //final OptionEntry optionEntry = new OptionEntry(customObjectForOneOptionItems.getOneOptionId(), customObjectForOneOptionItems.getOneOption());
        AppExecutor.getInstance().diskIO.execute(new Runnable() {
            @Override
            public void run() {
                database.OptionDao().deleteOption(optionEntry);
            }
        });


    }

    void calling() {
        database = OptionDatabase.getInstance(getApplicationContext());
        btnAdd = findViewById(R.id.btnAdd);
        optionListView = findViewById(R.id.optionListView);

        enterYourOptionEditText = findViewById(R.id.enterYourOptionEditText);
        numberOfOptionTextView = findViewById(R.id.numberOfOptionTextView);
    }
}
