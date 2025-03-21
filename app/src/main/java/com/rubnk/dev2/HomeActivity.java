package com.rubnk.dev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeText;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String login = prefs.getString("loggedInUser", "Utilisateur");

        welcomeText.setText(getString(R.string.welcome, login));

        btnLogout.setOnClickListener(v -> {
            prefs.edit().remove("loggedInUser").apply();
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}
