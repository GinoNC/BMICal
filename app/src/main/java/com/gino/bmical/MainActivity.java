package com.gino.bmical;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        tvResult = findViewById(R.id.tvResult);

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> {
            inputClear();
        });

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(v -> {
            if (inputValidation()) {
                calculateBMI();
            }
        });
    }


    private void inputClear() {
        etWeight.setText("");
        etHeight.setText("");
        tvResult.setText("");
    }

    private Boolean inputValidation() {
        tvResult.setText("");
        tvResult.setTextColor(getResources().getColor(R.color.black));

        String wei = etWeight.getText().toString();
        String hei = etHeight.getText().toString();
        if (wei.isEmpty() || hei.isEmpty()) {
            tvResult.setText("!! Please enter weight and height !!");
            return false;
        }
        if (Float.parseFloat(wei) <= 0 || Float.parseFloat(hei) <= 0) {
            tvResult.setText("!! Please enter valid weight and height !!");
            return false;
        }

        return true;
    }

    private void calculateBMI() {
        float weight = Float.parseFloat(etWeight.getText().toString());
        float height = Float.parseFloat(etHeight.getText().toString());

        float bmi = weight / (height * height);

        if (bmi < 16)
        {
            tvResult.setText("Underweight (Severe Thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.yellow));
        }
        else if (bmi < 17)
        {
            tvResult.setText("Underweight (Moderate thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.yellow));
        }
        else if (bmi < 18.5)
        {
            tvResult.setText("Underweight (Mild thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.yellow));
        }
        else if (bmi < 25)
        {
            tvResult.setText("Normal range");
            tvResult.setTextColor(getResources().getColor(R.color.green));
        }
        else if (bmi < 30)
        {
            tvResult.setText("Overweight (Pre-obese)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else if (bmi < 35)
        {
            tvResult.setText("Obese (Class I)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else if (bmi < 40)
        {
            tvResult.setText("Obese (Class II)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else
        {
            tvResult.setText("Obese (Class III)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
    }
}