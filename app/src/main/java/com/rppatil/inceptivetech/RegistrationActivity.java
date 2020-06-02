package com.rppatil.inceptivetech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.rppatil.inceptivetech.common.ConfigurationSettings;
import com.rppatil.inceptivetech.common.CustomSearchableSpinner;
import com.rppatil.inceptivetech.common.Validation;
import com.rppatil.inceptivetech.dbhandler.Repository.CityInfoRepository;
import com.rppatil.inceptivetech.dbhandler.Repository.StateInfoRepository;
import com.rppatil.inceptivetech.dbhandler.Repository.UserRegistrationRepository;
import com.rppatil.inceptivetech.dbhandler.entity.CityInfo;
import com.rppatil.inceptivetech.dbhandler.entity.StateInfo;
import com.rppatil.inceptivetech.dbhandler.entity.UserRegistration;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText et_firstname, et_lastname, et_email, et_mobile_number, et_password, et_confirmpassword;
    CustomSearchableSpinner spn_state, spn_district;
    Button btn_submit;
    int selectedStateid, selectedCityid;
    String[] StateList = {"Maharashtra", "Karnataka", "Gujarat"};
    int[] StateListId = {1,2,3};
    String[] CityList = {"Pune", "Sangli", "Satara", "Bangalore", "Belgaum", "Gulbarga", "Ahmedabad", "Vadodara", "Surat"};
    int[] StateDistrictId = {1,1,1,2,2,2,3,3,3};
    int[] DistrictId = {1,2,3,1,2,3,1,2,3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_email = findViewById(R.id.et_email);
        et_mobile_number = findViewById(R.id.et_mobile_number);
        et_password = findViewById(R.id.et_password);
        et_confirmpassword = findViewById(R.id.et_confirmpassword);
        spn_state = findViewById(R.id.spn_state);
        spn_district = findViewById(R.id.spn_district);
        btn_submit = findViewById(R.id.btn_submit);
        if (UserRegistrationRepository.getInstance(getApplicationContext()).getCount() > 0) {
            UserRegistrationRepository.getInstance(getApplicationContext()).deleteAll();
        }
        if (StateInfoRepository.getInstance(getApplicationContext()).getCount() == 0) {
            AddData();
            ConfigurationSettings.loadStateDataSpinner(getApplicationContext(), getString(R.string.select_state), getString(R.string.select_district), spn_state, spn_district);
        } else {
            ConfigurationSettings.loadStateDataSpinner(getApplicationContext(), getString(R.string.select_state), getString(R.string.select_district), spn_state, spn_district);
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }


    public void AddData() {
        AddState();
        AddCity();
    }

    public void AddState() {
        for(int i = 0; i < StateList.length; i++ ) {
            StateInfo stateInfos = new StateInfo();
            stateInfos.setState_id(StateListId[i]);
            stateInfos.setState_name(StateList[i]);
            StateInfoRepository.getInstance(getApplicationContext()).insert(stateInfos);
        }
    }

    public void AddCity() {
        for(int i = 0; i < CityList.length; i++ ) {
            CityInfo cityInfo = new CityInfo();
            cityInfo.setState_id(StateDistrictId[i]);
            cityInfo.setCity_id(DistrictId[i]);
            cityInfo.setCity_name(CityList[i]);
            CityInfoRepository.getInstance(getApplicationContext()).insert(cityInfo);
        }
    }

    public void SaveData() {
        selectedStateid = ConfigurationSettings.getSelectedStateId();
        selectedCityid = ConfigurationSettings.getSelectedDistrictId();
        if (Validation.isValidate(et_firstname, "Please Enter First Name")
                && Validation.isValidate(et_lastname, "Please Enter Last Name")
                && Validation.isValidateEmail(et_email, "Please Enter Valid Email Address")
                && Validation.isValidContact(et_mobile_number, "Please Enter Valid Mobile Number")
                && Validation.isValidSelection(selectedStateid, spn_state, getApplicationContext(), "Please Select State")
                && Validation.isValidSelection(selectedCityid, spn_district, getApplicationContext(), "Please Select City")
                && Validation.isValidate(et_password, "Please Enter Password")
                && Validation.isValidate(et_confirmpassword, "Please Enter Confirmation Password")) {
            if (et_password.getText().toString().equals(et_confirmpassword.getText().toString())) {
                UserRegistration userRegistration = new UserRegistration();
                userRegistration.setFirst_name(et_firstname.getText().toString().trim());
                userRegistration.setLast_name(et_lastname.getText().toString().trim());
                userRegistration.setEmail_address(et_email.getText().toString().trim());
                userRegistration.setMobile_no(et_mobile_number.getText().toString().trim());
                userRegistration.setState_id(selectedCityid);
                userRegistration.setCity_id(selectedCityid);
                userRegistration.setPassword(et_password.getText().toString());
                UserRegistrationRepository.getInstance(getApplicationContext()).insert(userRegistration);
                finish();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            } else {
                Validation.DisplayCenterToast(getApplicationContext(), "Password and Confirm Password Should be Same");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}