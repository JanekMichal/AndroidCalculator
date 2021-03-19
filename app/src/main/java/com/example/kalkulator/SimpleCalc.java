package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class SimpleCalc extends AppCompatActivity {

    private TextView resultView;
    private float result = 0;
    private float resultInMemory = 0;
    private int operation = 0; //0 - none, 1 - add, 2 - sub, 3 - mul, 4 - div
    private int isOperationSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        resultView = findViewById(R.id.resultView);
    }


    public void ACClick(View view) {
        result = 0;
        resultInMemory = 0;
        operation = 0;
        resultView.setText(valueOf(result));


    }

    private void numberClick(int number) {
        if (result == 0) {
            result = number;
        } else if (isOperationSelected == 0 && resultInMemory != 0) {
            result = number;
        } else {
            result = result * 10 + number;
        }
        resultView.setText(valueOf(result));
    }

    public void equalsClick(View view) {
        if (operation == 0) {
            resultInMemory = result;
        } else if (operation == 1) {
            resultInMemory += result;
        } else if (operation == 2) {
            resultInMemory -= result;
        } else if (operation == 3) {
            resultInMemory *= result;
        } else if (operation == 4) {
            resultInMemory /= result;
        }
        isOperationSelected = 0;

        resultView.setText(valueOf(resultInMemory));
    }

    public void plusClick(View view) {
        if (result != 0 && resultInMemory != 0 && isOperationSelected == 1) {
            resultInMemory += result;
            resultView.setText(valueOf(resultInMemory));
        } else if (resultInMemory == 0) {
            resultInMemory = result;
        }
        isOperationSelected = 1;
        result = 0;
        operation = 1;

    }

    public void minusClick(View view) {
        if (result != 0 && resultInMemory != 0 && isOperationSelected == 1) {
            resultInMemory -= result;
            resultView.setText(valueOf(resultInMemory));
        } else if (resultInMemory == 0) {
            resultInMemory = result;
        }
        isOperationSelected = 1;
        result = 0;
        operation = 2;
    }

    public void mulClick(View view) {
        if (result != 0 && resultInMemory != 0 && isOperationSelected == 1) {
            resultInMemory *= result;
            resultView.setText(valueOf(resultInMemory));
        } else if (resultInMemory == 0) {
            resultInMemory = result;
        }
        isOperationSelected = 1;
        result = 0;
        operation = 3;
    }

    public void divClick(View view) {
        if (result != 0 && resultInMemory != 0 && isOperationSelected == 1) {
            resultInMemory /= result;
            resultView.setText(valueOf(resultInMemory));
        } else if (resultInMemory == 0) {
            resultInMemory = result;
        }
        isOperationSelected = 1;
        result = 0;
        operation = 4;
    }

    public void percentClick(View view) {

    }

    public void changeSignClick(View view) {
        if (resultInMemory != 0 && result == 0) {
            resultInMemory *= -1;
            resultView.setText(valueOf(resultInMemory));
        } else if (result != 0) {
            result *= -1;
            resultView.setText(valueOf(result));
        }
    }

    public void comaClick(View view) {

    }

    public void button0Click(View view) {
        numberClick(0);
    }

    public void button1Click(View view) {
        numberClick(1);
    }

    public void button2Click(View view) {
        numberClick(2);
    }

    public void button3Click(View view) {
        numberClick(3);
    }

    public void button4Click(View view) {
        numberClick(4);
    }

    public void button5Click(View view) {
        numberClick(5);
    }

    public void button6Click(View view) {
        numberClick(6);
    }

    public void button7Click(View view) {
        numberClick(7);
    }

    public void button8Click(View view) {
        numberClick(8);
    }

    public void button9Click(View view) {
        numberClick(9);
    }


}