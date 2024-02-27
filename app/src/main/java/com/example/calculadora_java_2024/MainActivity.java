package com.example.calculadora_java_2024;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora_java_2024.R;

public class MainActivity extends AppCompatActivity {

    private EditText operationsLabel;
    private StringBuilder currentInput;
    private String operand;
    private double num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationsLabel = findViewById(R.id.operations_label);
        currentInput = new StringBuilder();
        operand = "";

        setButtonClickListeners();
    }

    private void setButtonClickListeners()
    {
        setNumberClickListener(R.id.btn0, "0");
        setNumberClickListener(R.id.btn1, "1");
        setNumberClickListener(R.id.btn2, "2");
        setNumberClickListener(R.id.btn3, "3");
        setNumberClickListener(R.id.btn4, "4");
        setNumberClickListener(R.id.btn5, "5");
        setNumberClickListener(R.id.btn6, "6");
        setNumberClickListener(R.id.btn7, "7");
        setNumberClickListener(R.id.btn8, "8");
        setNumberClickListener(R.id.btn9, "9");

        setOperatorClickListener(R.id.btnSum, "+");
        setOperatorClickListener(R.id.btnRes, "-");
        setOperatorClickListener(R.id.btnMul, "x");
        setOperatorClickListener(R.id.btnDiv, "/");

        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });

        findViewById(R.id.ctrlC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });

        findViewById(R.id.ctrlErase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseLastCharacter();
            }
        });

        findViewById(R.id.btnPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendDot();
            }
        });
    }

    private void setNumberClickListener(int buttonId, final String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToInput(number);
            }
        });
    }

    private void setOperatorClickListener(int buttonId, final String operator) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator(operator);
            }
        });
    }

    private void appendToInput(String value) {
        currentInput.append(value);
        updateDisplay();
    }

    private void setOperator(String operator) {
        operand = operator;
        num1 = Double.parseDouble(currentInput.toString());
        currentInput.setLength(0);
        updateDisplay();
    }

    private void calculateResult() {
        if (!currentInput.toString().isEmpty()) {
            num2 = Double.parseDouble(currentInput.toString());
            double result = performOperation();
            currentInput.setLength(0);
            currentInput.append(result);
            updateDisplay();
            operand = "";
        }
    }

    private double performOperation() {
        switch (operand) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    clearInput();
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void updateDisplay() {
        operationsLabel.setText(currentInput.toString());
    }

    private void clearInput() {
        currentInput.setLength(0);
        operand = "";
        updateDisplay();
    }

    private void eraseLastCharacter() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            updateDisplay();
        }
    }

    private void appendDot() {
        if (!currentInput.toString().contains(".")) {
            appendToInput(".");
        }
    }
}
