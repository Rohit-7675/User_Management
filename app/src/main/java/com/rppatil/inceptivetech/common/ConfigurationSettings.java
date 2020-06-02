package com.rppatil.inceptivetech.common;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rppatil.inceptivetech.R;
import com.rppatil.inceptivetech.dbhandler.Repository.CityInfoRepository;
import com.rppatil.inceptivetech.dbhandler.Repository.StateInfoRepository;
import com.rppatil.inceptivetech.dbhandler.entity.CityInfo;
import com.rppatil.inceptivetech.dbhandler.entity.StateInfo;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationSettings {
    private static int selectedStateId;
    private static int selectedDistrictId;
    public static String DEFAULT_SPINNER = "1";
    public static String SELECTED_ITEM_SPINNER = "2";


    private static List<StateInfo> getStatesFromLocalDB(Context context) {
        List<StateInfo> states = StateInfoRepository.getInstance(context).getAll();
        return states;
    }

    private static List<CityInfo> getDistrictsByStateIdFromLocalDB(Context context, int stateId) {
        List<CityInfo> districts = CityInfoRepository.getInstance(context).getStateWiseCity(stateId);
        return districts;
    }

    public static void loadStateDataSpinner(final Context context, String selectedState, final String district, CustomSearchableSpinner spn_state, final CustomSearchableSpinner spn_district) {
        final ArrayList<String> states_arrayList = new ArrayList<>();
        final ArrayList<Integer> id_arrayList = new ArrayList<>();

        List<StateInfo> stateList = ConfigurationSettings.getStatesFromLocalDB(context);

        states_arrayList.add(0, context.getResources().getString(R.string.select_state));
        id_arrayList.add(0, 0);

        for (int i = 0; i < stateList.size(); i++) {
            states_arrayList.add(i + 1, stateList.get(i).getState_name());
            id_arrayList.add(i + 1, stateList.get(i).getState_id());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, states_arrayList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_state.setAdapter(dataAdapter);
        spn_state.setSelection(getSelectedCustomSpinnerItemIndex(spn_state, selectedState));
        spn_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStateId = id_arrayList.get(position);
                // empty district list onchange state
                List<String> districts_arrayList = new ArrayList<>();
                districts_arrayList.add(0, context.getResources().getString(R.string.select_district));
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, districts_arrayList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spn_district.setAdapter(dataAdapter);
                loadDistrictDataSpinner(context, String.valueOf(selectedStateId), district, spn_district);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public static void loadDistrictDataSpinner(Context context, String stateId, String selectedDistrict, CustomSearchableSpinner spn_district) {
        final ArrayList<String> districts_arrayList = new ArrayList<>();
        final ArrayList<Integer> district_ID_arrayList = new ArrayList<>();


        districts_arrayList.add(0, context.getResources().getString(R.string.select_district));
        district_ID_arrayList.add(0, 0);
        if (Integer.parseInt(stateId) > 0) {
            List<CityInfo> districtList = ConfigurationSettings.getDistrictsByStateIdFromLocalDB(context, Integer.parseInt(stateId));
            for (int i = 0; i < districtList.size(); i++) {
                districts_arrayList.add(i + 1, districtList.get(i).getCity_name());
                district_ID_arrayList.add(i + 1, districtList.get(i).getCity_id());
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, districts_arrayList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_district.setAdapter(dataAdapter);
        spn_district.setSelection(getSelectedCustomSpinnerItemIndex(spn_district, selectedDistrict));
        spn_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    selectedDistrictId = district_ID_arrayList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static int getSelectedStateId() {
        return selectedStateId;
    }

    public static int getSelectedDistrictId() {
        return selectedDistrictId;
    }

    public static int getSelectedCustomSpinnerItemIndex(CustomSearchableSpinner spinner, String spinnerItemToSelect) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(spinnerItemToSelect)) {
                index = i;
            }
        }
        return index;
    }

}
