package com.example.assign1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assign1.Adapter.RecordAdapter;
import com.example.assign1.DataBase.DatabaseQueryClass;
import com.example.assign1.Model.Record;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CowFragment extends Fragment {


    Button btnSaveEntry;
    ListView listView;
    ArrayList<Record> dataModels;

    private static RecordAdapter adapter;


    EditText id, weight, age;
    String sid, sweight, sage;

    public CowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cow, container, false);
        Bundle args = getArguments();
        int cowtagid = args.getInt("cow", 0);
        TextView tv1 = (TextView) view.findViewById((R.id.cowtitle));
        Constan.actualposition = cowtagid - 1;
        tv1.setText((MainActivity.pageNames[Constan.actualposition]));


        id = view.findViewById(R.id.id);
        weight = view.findViewById(R.id.weight);
        age = view.findViewById(R.id.age);
        listView = view.findViewById(R.id.listView);

        btnSaveEntry = view.findViewById(R.id.btnSaveEntry);
        btnSaveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sid = id.getText().toString();
                sweight = weight.getText().toString();
                sage = age.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateandTime = sdf.format(new Date());

                Record record = new Record(-1, sid, sweight, sage, String.valueOf(Constan.actualposition), currentDateandTime);
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());


                long id = databaseQueryClass.insertRecord(record);

                if (id > 0) {
                    record.setPid(id);
                }
            }
        });




        adapter= new RecordAdapter(dataModels,getActivity());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Record dataModel= dataModels.get(position);

                Toast.makeText(getActivity(), dataModel.getId()+"", Toast.LENGTH_SHORT).show();
                /*Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
            }
        });


        return view;
    }


}
