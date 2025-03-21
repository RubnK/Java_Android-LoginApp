package com.rubnk.dev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        EditText editLogin = findViewById(R.id.login);
        EditText editPassword = findViewById(R.id.password);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnGoLogin = findViewById(R.id.btnGoLogin);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);

        if (prefs.contains("loggedInUser")) {
            startActivity(new Intent(this, HomeActivity.class));
        }

        btnRegister.setOnClickListener(v -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_fields), Toast.LENGTH_SHORT).show();
            } else if (prefs.contains("user_" + login)) {
                Toast.makeText(this, getString(R.string.error_user_exists), Toast.LENGTH_SHORT).show();
            } else {
                prefs.edit().putString("user_" + login, password).apply();
                Toast.makeText(this, getString(R.string.success_registration), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            }
        });

        btnGoLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}
