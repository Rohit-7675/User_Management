package com.rppatil.inceptivetech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.rppatil.inceptivetech.common.Validation;
import com.rppatil.inceptivetech.dbhandler.Repository.UserRegistrationRepository;
import com.rppatil.inceptivetech.dbhandler.entity.UserRegistration;

import java.util.List;

public class ContactUs extends AppCompatActivity {
    TextInputEditText et_name, et_contactNo, et_email, et_message;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        et_name = findViewById(R.id.et_name);
        et_contactNo = findViewById(R.id.et_contactNo);
        et_email = findViewById(R.id.et_email);
        et_message = findViewById(R.id.et_message);
        btn_submit = findViewById(R.id.btn_submit);
        if (UserRegistrationRepository.getInstance(getApplicationContext()).getCount() > 0) {
            List<UserRegistration> userRegistrations = UserRegistrationRepository.getInstance(getApplicationContext()).getAll();
            et_name.setText(userRegistrations.get(0).getFirst_name() + " " + userRegistrations.get(0).getLast_name());
            et_contactNo.setText(userRegistrations.get(0).getMobile_no());
            et_email.setText(userRegistrations.get(0).getEmail_address());
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDetails();
            }
        });
    }

    public void sendDetails() {
        if (Validation.isValidate(et_name, "Please Enter Name")
                && Validation.isValidate(et_message, "Please Enter Last Name")
                && Validation.isValidContact(et_contactNo, "Please Enter Valid Mobile Number")
                && Validation.isValidateEmail(et_email, "Please Enter Valid Email Address")) {
            Validation.DisplayCenterToast(getApplicationContext(), "Thank you for your feedback!!!");
            Reset();
        }
    }

    public void Reset()
    {
        et_name.setText("");
        et_contactNo.setText("");
        et_email.setText("");
        et_message.setText("");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}
