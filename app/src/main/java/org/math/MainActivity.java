package org.math;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private OppMode mode;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMode(OppMode.PLUS);
        findViewById(R.id.modePlus).setOnClickListener(this);
        findViewById(R.id.modePlusPlus).setOnClickListener(this);
        findViewById(R.id.modeMinus).setOnClickListener(this);
        findViewById(R.id.modeMinusMinus).setOnClickListener(this);
        findViewById(R.id.modeMultiply).setOnClickListener(this);
        findViewById(R.id.modeDivide).setOnClickListener(this);

        findViewById(R.id.number0).setOnClickListener(this);
        findViewById(R.id.number1).setOnClickListener(this);
        findViewById(R.id.number2).setOnClickListener(this);
        findViewById(R.id.number3).setOnClickListener(this);
        findViewById(R.id.number4).setOnClickListener(this);
        findViewById(R.id.number5).setOnClickListener(this);
        findViewById(R.id.number6).setOnClickListener(this);
        findViewById(R.id.number7).setOnClickListener(this);
        findViewById(R.id.number8).setOnClickListener(this);
        findViewById(R.id.number9).setOnClickListener(this);

        findViewById(R.id.resultClear).setOnClickListener(this);
    }

    void setMode(OppMode mode) {
        this.mode = mode;
        TextView view = findViewById(R.id.operationText);
        switch (this.mode) {
            case PLUS:
                view.setText("+");
                break;
            case PLUS_PLUS:
                view.setText("+ ");
                break;
            case MINUS:
                view.setText("-");
                break;
            case MINUS_MINUS:
                view.setText("- ");
                break;
            case MULTIPLY:
                view.setText(".");
                break;
            case DIVIDE:
                view.setText(":");
                break;
        }
        generateNumbers();
    }


    void generateNumbers() {
        int one = -1, two = -1;
        switch (this.mode) {
            case PLUS:
                one = (int) (Math.random() * 9);
                two = (int) (Math.random() * 9);
                result = one + two;
                break;
            case PLUS_PLUS:
                one = (int) (Math.random() * 99);
                two = (int) (Math.random() * 99);
                result = one + two;
                break;
            case MINUS:
                one = (int) (Math.random() * 9);
                two = (int) (Math.random() * 9);
                if (one < two) {
                    int t = two;
                    two = one;
                    one = t;
                }
                result = one - two;
                break;
            case MINUS_MINUS:
                one = (int) (Math.random() * 99);
                two = (int) (Math.random() * 99);
                if (one < two) {
                    int t = two;
                    two = one;
                    one = t;
                }
                result = one - two;
                break;
            case MULTIPLY:
                one = (int) (Math.random() * 9);
                two = (int) (Math.random() * 9);
                result = one * two;
                break;
            case DIVIDE:
                one = 1 + (int) (Math.random() * 8);
                two = 1 + (int) (Math.random() * 8);
                result = one;
                one *= two;
                break;
        }
        TextView numberOne = findViewById(R.id.numberOne);
        numberOne.setText("" + one);
        TextView numberTwo = findViewById(R.id.numberTwo);
        numberTwo.setText("" + two);
        TextView numberResult = findViewById(R.id.numberResult);
        numberResult.setText("");
    }

    @Override
    public void onClick(View v) {
        TextView numberResult = findViewById(R.id.numberResult);
        switch (v.getId()) {
            case R.id.modePlus:
                setMode(OppMode.PLUS);
                break;
            case R.id.modePlusPlus:
                setMode(OppMode.PLUS_PLUS);
                break;
            case R.id.modeMinus:
                setMode(OppMode.MINUS);
                break;
            case R.id.modeMinusMinus:
                setMode(OppMode.MINUS_MINUS);
                break;
            case R.id.modeMultiply:
                setMode(OppMode.MULTIPLY);
                break;
            case R.id.modeDivide:
                setMode(OppMode.DIVIDE);
                break;
            case R.id.resultClear:
                numberResult.setText("");
                break;

            case R.id.number0:
                numberResult.setText(numberResult.getText() + "0");
                checkResult();
                break;
            case R.id.number1:
                numberResult.setText(numberResult.getText() + "1");
                checkResult();
                break;
            case R.id.number2:
                numberResult.setText(numberResult.getText() + "2");
                checkResult();
                break;
            case R.id.number3:
                numberResult.setText(numberResult.getText() + "3");
                checkResult();
                break;
            case R.id.number4:
                numberResult.setText(numberResult.getText() + "4");
                checkResult();
                break;
            case R.id.number5:
                numberResult.setText(numberResult.getText() + "5");
                checkResult();
                break;
            case R.id.number6:
                numberResult.setText(numberResult.getText() + "6");
                checkResult();
                break;
            case R.id.number7:
                numberResult.setText(numberResult.getText() + "7");
                checkResult();
                break;
            case R.id.number8:
                numberResult.setText(numberResult.getText() + "8");
                checkResult();
                break;
            case R.id.number9:
                numberResult.setText(numberResult.getText() + "9");
                checkResult();
                break;
            default:
                break;
        }
    }

    void checkResult() {
        TextView numberResult = findViewById(R.id.numberResult);
        try {
            int value = Integer.parseInt(numberResult.getText().toString(), 10);
            if (value == result) {
                Toast.makeText(getApplicationContext(), "Bravo !!!", Toast.LENGTH_LONG).show();
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        runOnUiThread(() -> setMode(mode));
                    }
                }, 1300);
            }
        } catch (Exception e) {
            // not interested
            e.printStackTrace();
        }
    }
}