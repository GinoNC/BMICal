package com.gino.bmical;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // ===== declare all components
    RadioGroup rgWeight;
    RadioButton rbWeightKG, rbWeightLBS;
    EditText etWeight, etHeight;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ===== initial
        // ===== connect all components
        rgWeight = findViewById(R.id.rgWeight);
        rbWeightKG = findViewById(R.id.rbWeightKG);
        rbWeightLBS = findViewById(R.id.rbWeightLBS);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        tvResult = findViewById(R.id.tvResult);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        // ===== preset input
        rbWeightKG.setChecked(true);

        // ===== set listener
        rbWeightKG.setOnClickListener(v -> {
            if (inputValidation()) {
                calculateBMI();
            }
        });
        rbWeightLBS.setOnClickListener(v -> {
            if (inputValidation()) {
                calculateBMI();
            }
        });

        btnClear.setOnClickListener(v -> {
            inputClear();
        });

        btnCalculate.setOnClickListener(v -> {
            if (inputValidation()) {
                calculateBMI();
            }
        });
    }

    // ===== reset all input
    private void inputClear() {
        rbWeightKG.setChecked(true);
        etWeight.setText("");
        etHeight.setText("");
        tvResult.setText("");
    }

    // ===== check user input
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

    // ===== calculation
    private void calculateBMI() {
        float weight = Float.parseFloat(etWeight.getText().toString());
        float height = Float.parseFloat(etHeight.getText().toString());

        float bmi = 0;
        if (rbWeightKG.isChecked()) bmi = weight / (height * height);
        if (rbWeightLBS.isChecked()) bmi = (float) (weight * 0.45 / (height * height));

        if (bmi < 16)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nUnderweight (Severe Thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.blue));
        }
        else if (bmi < 17)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nUnderweight (Moderate thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.blue));
        }
        else if (bmi < 18.5)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nUnderweight (Mild thinness)");
            tvResult.setTextColor(getResources().getColor(R.color.blue));
        }
        else if (bmi < 25)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nNormal range");
            tvResult.setTextColor(getResources().getColor(R.color.green));
        }
        else if (bmi < 30)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nOverweight (Pre-obese)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else if (bmi < 35)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nObese (Class I)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else if (bmi < 40)
        {
            tvResult.setText("BMI index: " + String.valueOf(bmi) + "\nObese (Class II)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
        else
        {
            tvResult.setText("BMI index: " + bmi + "\nObese (Class III)");
            tvResult.setTextColor(getResources().getColor(R.color.red));
        }
    }
}