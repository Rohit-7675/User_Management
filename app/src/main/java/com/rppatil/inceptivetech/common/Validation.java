package com.rppatil.inceptivetech.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Validation {

    private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean isValidContact(TextInputEditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            editText.requestFocus();
            return false;
        } else if (editText.getText().length() == 10) {
            editText.setError(null);
            return true;
        } else {
            editText.setError(error);
            editText.requestFocus();
            return false;
        }
    }

    public static boolean isValidate(TextInputEditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            editText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidateEmail(TextInputEditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            editText.requestFocus();
            return false;
        } else if (editText.getText().toString().matches(emailPattern)) {
            editText.setError(null);
            return true;
        } else {
            editText.setError(error);
            editText.requestFocus();
            return false;
        }
    }

    public static boolean isValidSelection(int selectedid, CustomSearchableSpinner customSearchableSpinner, Context ctx, String error) {
        if (selectedid == 0 || customSearchableSpinner.getSelectedItemPosition() == 0) {
            DisplayCenterToast(ctx, error);
            return false;
        } else {
            return true;
        }
    }

    public static void DisplayCenterToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}
