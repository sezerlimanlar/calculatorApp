package com.example.basic_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText number;
    TextView result, operand;
    String operation = "+";
    Double num1 = null;
    Double num2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (EditText) findViewById(R.id.et_number);
        result = (TextView) findViewById(R.id.tv_result);
        operand = (TextView) findViewById(R.id.tv_operand);

        Button button0 = (Button) findViewById(R.id.button_0);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.button_5);
        Button button6 = (Button) findViewById(R.id.button_6);
        Button button7 = (Button) findViewById(R.id.button_7);
        Button button8 = (Button) findViewById(R.id.button_8);
        Button button9 = (Button) findViewById(R.id.button_9);

        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonPlus = (Button) findViewById(R.id.button_plus);
        Button buttonMinus = (Button) findViewById(R.id.button_minus);
        Button buttonDiv = (Button) findViewById(R.id.button_div);
        Button buttonMulti = (Button) findViewById(R.id.button_multi);
        Button buttonMod = (Button) findViewById(R.id.button_mod);
        Button buttonExpo = (Button) findViewById(R.id.button_expo);
        Button buttonSqrt = (Button) findViewById(R.id.button_sqrt);
        Button buttonDelete = (Button) findViewById(R.id.button_delete);
        Button buttonAllDelete = (Button) findViewById(R.id.button_allDelete);
        Button buttonEquals = (Button) findViewById(R.id.button_equals);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                number.append(button.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = number.getText().toString();
                int lenght = word.length();
                if (lenght > 0) {
                    number.setText(word.substring(0, lenght - 1));
                }
            }
        });

        buttonAllDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(null);
                result.setText(null);
                operand.setText(null);
                num1 = null;

            }
        });
        View.OnClickListener operandListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = number.getText().toString();
                try {
                    Double doubleValue = Double.parseDouble(value);
                    ActiveOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    number.setText("");
                }
                operation = op;
                operand.setText(operation);

            }
        };
        buttonPlus.setOnClickListener(operandListener);
        buttonMinus.setOnClickListener(operandListener);
        buttonDiv.setOnClickListener(operandListener);
        buttonMulti.setOnClickListener(operandListener);
        buttonEquals.setOnClickListener(operandListener);
        buttonSqrt.setOnClickListener(operandListener);
        buttonMod.setOnClickListener(operandListener);
        buttonExpo.setOnClickListener(operandListener);


    }

    private void ActiveOperation(Double value, String op) {
        if (num1 == null) {
            num1 = value;
        } else {
            num2 = value;
            if (operation.equals("=")) {
                operation = op;
            }
            switch (operation) {
                case "=":
                    num1 = num2;
                    break;
                case "+":
                    num1 += num2; //num1 = num1 +num2;
                    break;
                case "—":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "÷":
                    if (num2 == 0) {
                        num1 = 0.0;
                    } else {
                        num1 /= num2;
                    }
                    break;
                case "xⁿ":
                    num1 = Math.pow(num1, num2);
                    break;
                case "√":
                    num1 = Math.pow(num2, 1 / num1);
                    break;
                case "%":
                    num1 = Math.IEEEremainder(num1, num2);
                    break;
            }
        }
        result.setText(num1.toString());
        number.setText("");
        operand.setText(operation);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("OPERATION", operation);
        if(num1 != null)
        {
            outState.putDouble("VALUE",num1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operation = savedInstanceState.getString("OPERATION");
        num1 = savedInstanceState.getDouble("VALUE");
        result.setText(String.valueOf(num1));
        operand.setText(operation);
    }
}