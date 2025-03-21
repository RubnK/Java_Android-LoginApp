package com.rubnk.dev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText editLogin = findViewById(R.id.login);
        EditText editPassword = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnGoRegister = findViewById(R.id.btnGoRegister);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);

        if (prefs.contains("loggedInUser")) {
            startActivity(new Intent(this, HomeActivity.class));
        }

        btnLogin.setOnClickListener(v -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            String storedPassword = prefs.getString("user_" + login, null);

            if (storedPassword == null) {
                Toast.makeText(this, getString(R.string.error_user_not_found), Toast.LENGTH_SHORT).show();
                return;
            } else if (!storedPassword.equals(password)) {
                Toast.makeText(this, getString(R.string.error_wrong_password), Toast.LENGTH_SHORT).show();
            } else {
                prefs.edit().putString("loggedInUser", login).apply();
                startActivity(new Intent(this, HomeActivity.class));
            }
        });

        btnGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
