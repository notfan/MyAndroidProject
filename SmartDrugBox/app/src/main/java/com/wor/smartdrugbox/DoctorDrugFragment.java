package com.wor.smartdrugbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DoctorDrugFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;
    List<Map<String, Object>> list;

    public DoctorDrugFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorDrugFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorDrugFragment newInstance(String param1, String param2) {
        DoctorDrugFragment fragment = new DoctorDrugFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_doctor_drug, container, false);
        listView = (ListView)rootView.findViewById(R.id.listView);
        refreshListItems();
        return rootView;
    }

    private void refreshListItems() {
        list = buildListForSimpleAdapter();
        SimpleAdapter bar = new SimpleAdapter(getActivity(), list, R.layout.drug_row,
                new String[] { "drug_name", "desc", "drug_img" }, new int[] { R.id.drug_name,
                R.id.desc, R.id.drug_img });
        listView.setAdapter(bar);
        listView.setOnItemClickListener(this);
        listView.setSelection(0);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
        // Build a map for the attributes
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("drug_name", "药片1 1");
        map.put("desc", "早饭前服用");
        map.put("drug_img", R.drawable.tablet_white);
        list.add(map);

        return list;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DrugSettingActivity.class);
        startActivity(intent);
    }
}
