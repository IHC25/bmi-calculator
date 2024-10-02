package com.imran.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edWeight,edFeet,edInches;
    Button calculateButton;
    TextView resultDisplay,bmiLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplication);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edWeight = findViewById(R.id.edWeight);
        edFeet = findViewById(R.id.edFeet);
        edInches = findViewById(R.id.edInches);
        calculateButton = findViewById(R.id.calculateButton);
        resultDisplay = findViewById(R.id.resultDisplay);
        bmiLevel = findViewById(R.id.bmiLevel);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sWeight, sFeet, sInches;
                Double userWeight,userFeet, userInches, userHeight, bmiResult;

                sWeight = edWeight.getText().toString();
                sFeet = edFeet.getText().toString();
                sInches = edInches.getText().toString();

                userWeight = Double.parseDouble(sWeight);
                userFeet = Double.parseDouble(sFeet);
                userInches = Double.parseDouble(sInches);
                userHeight = (0.3048 * userFeet) + (0.0254 * userInches);

                bmiResult = userWeight/(userHeight*userHeight);
                String bmiResultRounded = String.format("%.1f",bmiResult);

                resultDisplay.setText(bmiResultRounded);

                if(bmiResult<18.5){
                    bmiLevel.setText("Underweight");
                    bmiLevel.setTextColor(getResources().getColor(R.color.light_green));
                    bmiLevel.setVisibility(View.VISIBLE);
                } else if (bmiResult>18.5 && bmiResult<=24.9) {
                    bmiLevel.setText("Normal");
                    bmiLevel.setTextColor(getResources().getColor(R.color.green));
                    bmiLevel.setVisibility(View.VISIBLE);
                } else if (bmiResult>24.9 && bmiResult<=29.9) {
                    bmiLevel.setText("Excess Weight");
                    bmiLevel.setTextColor(getResources().getColor(R.color.orange));
                    bmiLevel.setVisibility(View.VISIBLE);
                } else if (bmiResult>29.9 && bmiResult<=34.9) {
                    bmiLevel.setText("Obesity");
                    bmiLevel.setTextColor(getResources().getColor(R.color.light_red));
                    bmiLevel.setVisibility(View.VISIBLE);
                } else {
                    bmiLevel.setText("Extremely Obese");
                    bmiLevel.setTextColor(getResources().getColor(R.color.red));
                    bmiLevel.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}