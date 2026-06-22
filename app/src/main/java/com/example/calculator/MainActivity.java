package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etNum1, etNum2;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        txtResult = findViewById(R.id.txtResult);

        btnAdd.setOnClickListener(v -> performCalculation(new Addition()));
        btnSub.setOnClickListener(v -> performCalculation(new Subtraction()));
        btnMul.setOnClickListener(v -> performCalculation(new Multiplication()));
        btnDiv.setOnClickListener(v -> performCalculation(new Division()));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        private void performCalculation(Operation operation) {
        String input1 = etNum1.getText().toString().trim();
        String input2 = etNum2.getText().toString().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);

            if (operation instanceof Division && num2 == 0) {
                Toast.makeText(this, "Cannot divide by zero!", Toast.LENGTH_SHORT).show();
                return;
            }

            Calculator calc = new Calculator();
            calc.setNum1(num1);
            calc.setNum2(num2);

            double result = operation.calculate(calc.getNum1(), calc.getNum2());
            txtResult.setText("Result: " + result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
