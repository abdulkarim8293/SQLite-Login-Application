package com.abdulkarim.loginapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdulkarim.loginapplication.R;
import com.abdulkarim.loginapplication.database.DatabaseHelper;
import com.abdulkarim.loginapplication.database.DatabaseManager;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtUserName, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnSignUp);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseHelper.USER_NAME, edtUserName.getText().toString());
                    contentValues.put(DatabaseHelper.USER_EMAIL, edtEmail.getText().toString());
                    contentValues.put(DatabaseHelper.USER_PASSWORD, edtPassword.getText().toString());

                    database = DatabaseManager.getInstance().openDatabase();
                    long checked = database.insert(DatabaseHelper.TABLE_USER, null, contentValues);

                    if (checked != -1) {
                        message("Account Created" + checked);
                        //startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                    } else {
                        message("This user is already exists" + checked);
                    }

                    DatabaseManager.getInstance().closeDatabase();

                } catch (Exception e) {
                    Toast.makeText(SignUpActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
