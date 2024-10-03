package com.imran.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edWeight,edFeet,edInches;
    Button calculateButton;
    TextView resultDisplay,bmiLevel, bmiComments, requiredWeight;
    ImageView footerImage;

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

        //Linking with xml
        edWeight = findViewById(R.id.edWeight);
        edFeet = findViewById(R.id.edFeet);
        edInches = findViewById(R.id.edInches);
        calculateButton = findViewById(R.id.calculateButton);
        resultDisplay = findViewById(R.id.resultDisplay);
        bmiLevel = findViewById(R.id.bmiLevel);
        bmiComments = findViewById(R.id.bmiComments);
        footerImage = findViewById(R.id.footerImage);
        requiredWeight = findViewById(R.id.requiredWeight);

        //Functionality of calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sWeight, sFeet, sInches;
                double userWeight,userFeet, userInches, userHeight, bmiResult, weightRequired;

                sWeight = edWeight.getText().toString();
                sFeet = edFeet.getText().toString();
                sInches = edInches.getText().toString();


                //error handling of user clicking Calculate button without filling required information
                if(sWeight.isEmpty() || sFeet.isEmpty() || sInches.isEmpty()){
                    //showing user a toast message to fill the required info
                    Toast.makeText(MainActivity.this, "All information not given", Toast.LENGTH_SHORT).show();
                } else {
                    userWeight = Double.parseDouble(sWeight);
                    userFeet = Double.parseDouble(sFeet);
                    userInches = Double.parseDouble(sInches);
                    userHeight = (0.3048 * userFeet) + (0.0254 * userInches);

                    bmiResult = userWeight/(userHeight*userHeight);
                    Locale Locale = java.util.Locale.US;
                    String bmiResultRounded = String.format(Locale,"%.1f",bmiResult);


                    resultDisplay.setText(bmiResultRounded);

                    footerImage.setVisibility(View.GONE);

                    //required weight calculation
                    if (bmiResult<18.5){
                        weightRequired = (18.5 - bmiResult)*userHeight*userHeight;
                        String weightRequiredRounded = String.format(Locale,"%.1f",weightRequired);
                        requiredWeight.setText("Gain "+weightRequiredRounded+" kg");
                        requiredWeight.setVisibility(View.VISIBLE);
                    } else if (bmiResult>24.9){
                        weightRequired = (bmiResult - 24.9)*userHeight*userHeight;
                        String weightRequiredRounded = String.format(Locale,"%.1f",weightRequired);
                        requiredWeight.setText("Lose "+weightRequiredRounded+" kg");
                        requiredWeight.setVisibility(View.VISIBLE);
                    } else {
                        requiredWeight.setText("Maintain Current Weight");
                        requiredWeight.setVisibility(View.VISIBLE);
                    }


                    String underWeightAdvice, normalWeightAdvice, excessWeightAdvice, obesityAdvice;
                    underWeightAdvice = "A BMI of less than 18.5 indicates that you are underweight, so you may need to put on some weight. You are recommended to ask your doctor or a dietitian for advice.";
                    normalWeightAdvice = "A BMI of 18.5-24.9 indicates that you are at a healthy weight for your height. By maintaining a healthy weight, you lower your risk of developing serious health problems.";
                    excessWeightAdvice = "A BMI of 25-29.9 indicates that you are slightly overweight. You may be advised to lose some weight for health reasons. You are recommended to talk to your doctor or a dietitian for advice.";
                    obesityAdvice = "A BMI of over 30 indicates that you are heavily overweight. Your health may be at risk if you do not lose weight. You are recommended to talk to your doctor or a dietitian for advice.";
                    //Showing user their BMI level
                    if(bmiResult<18.5){
                        bmiLevel.setText("Underweight");
                        bmiComments.setText(underWeightAdvice);
                        bmiLevel.setTextColor(getResources().getColor(R.color.light_green));
                        bmiLevel.setVisibility(View.VISIBLE);
                        bmiComments.setVisibility(View.VISIBLE);

                    } else if (bmiResult>=18.5 && bmiResult<=24.9) {
                        bmiLevel.setText("Normal");
                        bmiComments.setText(normalWeightAdvice);
                        bmiLevel.setTextColor(getResources().getColor(R.color.green));
                        bmiLevel.setVisibility(View.VISIBLE);
                        bmiComments.setVisibility(View.VISIBLE);

                    } else if (bmiResult>24.9 && bmiResult<=29.9) {
                        bmiLevel.setText("Excess Weight");
                        bmiComments.setText(excessWeightAdvice);
                        bmiLevel.setTextColor(getResources().getColor(R.color.orange));
                        bmiLevel.setVisibility(View.VISIBLE);
                        bmiComments.setVisibility(View.VISIBLE);
                    } else if (bmiResult>29.9 && bmiResult<=34.9) {
                        bmiLevel.setText("Obesity");
                        bmiComments.setText(obesityAdvice);
                        bmiLevel.setTextColor(getResources().getColor(R.color.light_red));
                        bmiLevel.setVisibility(View.VISIBLE);
                        bmiComments.setVisibility(View.VISIBLE);
                    } else {
                        bmiLevel.setText("Extremely Obese");
                        bmiComments.setText(obesityAdvice);
                        bmiLevel.setTextColor(getResources().getColor(R.color.red));
                        bmiLevel.setVisibility(View.VISIBLE);
                        bmiComments.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }
}