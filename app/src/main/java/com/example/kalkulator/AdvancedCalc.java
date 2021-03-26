package com.example.kalkulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;


import static java.lang.Character.isDigit;
import static java.lang.Math.*;


public class AdvancedCalc extends AppCompatActivity {

    private String current_value = "0";
    private TextView result_basic;
    final private int max_length = 20;
    private final MathContext m = new MathContext(max_length / 2);
    final private String error_message = "Error";
    private BigDecimal memory_result = new BigDecimal("0.0", m);
    private String last_action = null;
    private boolean should_reset_result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);
        result_basic = findViewById(R.id.resultView_advanced);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("current_value", current_value);
        savedInstanceState.putDouble("memory_result", (memory_result.doubleValue()));
        savedInstanceState.putString("last_action", last_action);
        savedInstanceState.putBoolean("should_reset_result", should_reset_result);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        current_value = savedInstanceState.getString("current_value");
        result_basic.setText(current_value);
        memory_result = BigDecimal.valueOf(savedInstanceState.getDouble("memory_result"));
        last_action = savedInstanceState.getString("last_action");
        should_reset_result = savedInstanceState.getBoolean("should_reset_result");
    }

    private void countLastExpression() {
        should_reset_result = true;
        try {
            BigDecimal current_decimal = new BigDecimal(current_value);
            if (last_action == null) {
                memory_result = current_decimal;
                return;
            }
            switch (last_action) {
                case "+":
                    memory_result = memory_result.add(current_decimal, m);
                    break;
                case "-":
                    memory_result = memory_result.subtract(current_decimal, m);
                    break;
                case "*":
                    memory_result = memory_result.multiply(current_decimal, m);
                    break;
                case "/":
                    memory_result = memory_result.divide(current_decimal, m);
                    break;
                case "sin":
                    memory_result = memory_result.valueOf(sin(Math.toRadians(current_decimal.doubleValue())));
                    break;
                case "cos":
                    memory_result = memory_result.valueOf(cos(Math.toRadians(current_decimal.doubleValue())));
                    break;
                case "tan":
                    memory_result = memory_result.valueOf(tan(Math.toRadians(current_decimal.doubleValue())));
                    break;
                case "ln":
                    memory_result = memory_result.valueOf(log(current_decimal.doubleValue()));
                    break;
                case "log":
                    memory_result = memory_result.valueOf(log10(current_decimal.doubleValue()));
                    break;
                case "sqrt":
                    memory_result = memory_result.valueOf(sqrt(current_decimal.doubleValue()));
                    break;
                case "square":
                    memory_result = current_decimal.pow(2, m);
                    break;
                case "pow":
                    double d = current_decimal.doubleValue();
                    double value = memory_result.doubleValue();
                    value = pow(value, d);
                    memory_result = BigDecimal.valueOf(value);
                    break;
                case "percent":
                    BigDecimal bd = new BigDecimal("0.01");
                    bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    memory_result = current_decimal.multiply(bd, m);
                    break;
            }
            setResult(memory_result.stripTrailingZeros().toEngineeringString());
        } catch (Exception e) {
            clearAll();
            setResult(error_message);
            should_reset_result = true;
        }
    }

    private void clearAll() {
        resetResult();
        memory_result.equals(0.0);
        last_action = null;
        should_reset_result = false;
    }

    private void resetResult() {
        current_value = "0";
        result_basic.setText(current_value);
    }

    private void setResult(String result) {
        current_value = result;
        result_basic.setText(current_value);
    }

    private void pushToResult(String result) {
        if (should_reset_result) {
            resetResult();
            should_reset_result = false;
        }
        if (current_value.equals("0") || current_value.equals(error_message))
            current_value = "";
        if (current_value.length() >= max_length)
            return;
        current_value += result;
        result_basic.setText(current_value);
    }

    private char checkLastCharacterInResult() {
        return current_value.charAt(current_value.length() - 1);
    }

    public void ACClick(View view) {
        clearAll();
    }

    private void numberClick(String number) {
        pushToResult(number);
    }

    public void equalsClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = null;
        }
    }

    public void plusClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = "+";
        }
    }

    public void minusClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = "-";
        }
    }

    public void mulClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = "*";
        }

    }

    public void divClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = "/";
        }
    }

    public void delClick(View view) {
        if (current_value.equals(error_message))
            resetResult();
        if (current_value.length() > 0) {
            setResult(current_value.substring(0, current_value.length() - 1));
            if (current_value.isEmpty())
                resetResult();
        }
        should_reset_result = false;
    }

    public void changeSignClick(View view) {
        if (current_value.compareTo("0") != 0) {
            if (current_value.charAt(0) == '-') {
                current_value = current_value.substring(1);
            } else if (isDigit(current_value.charAt(0)))
                current_value = "-" + current_value;
            setResult(current_value);
        }
    }

    public void comaClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            if (current_value.equals("0") || should_reset_result)
                pushToResult("0.");
            else if (!current_value.contains("."))
                pushToResult(".");
        }
    }

    public void sinClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "sin";
            countLastExpression();
            last_action = null;
        }
    }

    public void cosClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "cos";
            countLastExpression();
            last_action = null;
        }
    }

    public void tanClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "tan";
            countLastExpression();
            last_action = null;
        }
    }

    public void lnClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "ln";
            countLastExpression();
            last_action = null;
        }
    }

    public void sqrtClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "sqrt";
            countLastExpression();
            last_action = null;
        }
    }

    public void x2Click(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "square";
            countLastExpression();
            last_action = null;
        }
    }

    public void xyClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            countLastExpression();
            last_action = "pow";
        }
    }

    public void logClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            last_action = "log";
            countLastExpression();
            last_action = null;
        }
    }

    public void percentClick(View view) {
        if (isDigit(checkLastCharacterInResult())) {
            String action_holder = last_action;
            BigDecimal memory_holder = new BigDecimal(memory_result.stripTrailingZeros().toEngineeringString());
            last_action = "percent";
            countLastExpression();
            last_action = action_holder;
            memory_result = memory_holder;
        }
    }

    public void button0Click(View view) {
        numberClick("0");
    }

    public void button1Click(View view) {
        numberClick("1");
    }

    public void button2Click(View view) {
        numberClick("2");
    }

    public void button3Click(View view) {
        numberClick("3");
    }

    public void button4Click(View view) {
        numberClick("4");
    }

    public void button5Click(View view) {
        numberClick("5");
    }

    public void button6Click(View view) {
        numberClick("6");
    }

    public void button7Click(View view) {
        numberClick("7");
    }

    public void button8Click(View view) {
        numberClick("8");
    }

    public void button9Click(View view) {
        numberClick("9");
    }

}