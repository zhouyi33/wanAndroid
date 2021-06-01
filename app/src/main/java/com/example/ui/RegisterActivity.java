    package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerin = (Button) findViewById(R.id.registerin);
        registerin.setOnClickListener(v-> {
            User.getInstance().register(((EditText)findViewById(R.id.logId)).getText().toString(),((EditText)findViewById(R.id.logpassword)).getText().toString());
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}