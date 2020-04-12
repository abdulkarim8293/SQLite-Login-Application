package com.abdulkarim.loginapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdulkarim.loginapplication.R;
import com.abdulkarim.loginapplication.database.DatabaseHelper;
import com.abdulkarim.loginapplication.model.User;

public class SignInActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String loginQ = "select * from " + DatabaseHelper.TABLE_USER + " where " +
                            DatabaseHelper.USER_EMAIL + " = '" + edtEmail.getText().toString() +
                            "' AND " + DatabaseHelper.USER_PASSWORD + " = '" + edtPassword.getText().toString()
                            + "'";

                    DatabaseManager.getInstance().openDatabase();

                    Cursor cursor = DatabaseManager.getInstance().getUserWithEmail(loginQ);

                    if (cursor != null && cursor.getCount() > 0) {

                        User user = new User();

                        cursor.moveToFirst();

                        while (!cursor.isAfterLast()) {

                            user.setUserId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_ID)));
                            user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_EMAIL)));
                            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD)));

                            cursor.moveToNext();
                        }

                        message("Login Success" + user.getUserId());
                        //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    } else {
                        message("Login Failed");
                    }
                    DatabaseManager.getInstance().closeDatabase();

                } catch (Exception e) {
                    Toast.makeText(SignInActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
