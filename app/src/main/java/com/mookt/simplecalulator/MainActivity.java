package com.mookt.simplecalulator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String firstNum;
    String secondNum;
    String next;
    String fixed;
    String operator;
    String result;
    boolean eqPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        firstNum = "";
        secondNum = "";
        next = "";
        fixed = "";
        operator = "";
        result = "";
        eqPressed = false;
    }

    public boolean isNumValid(String num){
        try {
            Double.parseDouble(num);
        }catch(Exception e){
            showInvalidNumMessage();
            return false;
        }
        return true;
    }

    public void showInvalidNumMessage(){
        AlertDialog alertDialog= new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Calculator");
        alertDialog.setMessage("Invalid Number");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void update(){
        String string = (String) textView.getText();
        int length = string.length();
        if(length > 0) {
            if (operator.isEmpty()) {
                if (firstNum.isEmpty() && result.isEmpty()){
                    if(isNumValid(string)) {
                        firstNum = string;
                    }else{
                        textView.setText("");
                    }
                }else if(firstNum.isEmpty() && !result.isEmpty()){
                    firstNum = result;
                    result = "";
                }
                operator = next;
                next = "";
                fixed = firstNum + operator + "\n";
            }else{
                if(length == fixed.length()){
                    fixed = string.substring(0,length-2) + next + "\n";
                    operator = next;
                    next = "";
                }else{
                    int i = string.indexOf("\n");
                    String s = string.substring(i+1, length);
                    if(isNumValid(s)) {
                        secondNum = s;
                        operate();
                        firstNum = result;
                        result = "";
                        secondNum = "";
                        operator = next;
                        next = "";
                        fixed = firstNum + operator + "\n";
                    }
                }
            }
            textView.setText(fixed);
        }

    }

    public void operate() {
        BigDecimal n1 = new BigDecimal(firstNum);
        BigDecimal n2 = new BigDecimal(secondNum);
        BigDecimal r = new BigDecimal(0);

        switch (operator) {
            case "+":
                r = n1.add(n2);
                break;
            case "-":
                r = n1.subtract(n2);
                break;
            case "*":
                r = n1.multiply(n2);
                break;
            case "/":
                try{
                    r = n1.divide(n2, 10, RoundingMode.HALF_EVEN);
                }catch(Exception e){
                    result = "UNDEFINED";
                }
                break;
        }

        if(result.isEmpty()){
            result = r.toString();
        }
        operator = "";
    }

    public void equal (View view){
        String string = (String) textView.getText();
        int length = string.length();
        if(!firstNum.isEmpty() && !string.endsWith("\n")){
            int i = string.indexOf("\n");
            String s = string.substring(i+1, length);
            if(isNumValid(s)) {
                eqPressed = true;
                secondNum = s;
                operate();
                textView.setText(result);
                firstNum = "";
                secondNum = "";
                fixed = "";
                next = "";
            }
        }
    }

    public void zero(View view){
        if(eqPressed){
            textView.setText("0");
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "0");
        }
        eqPressed = false;
    }

    public void one(View view){
        if(eqPressed){
            textView.setText("1");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "1");
        }
        eqPressed = false;
    }

    public void two(View view){
        if(eqPressed){
            textView.setText("2");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "2");
        }
        eqPressed = false;
    }

    public void three(View view){
        if(eqPressed){
            textView.setText("3");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "3");
        }
        eqPressed = false;
    }

    public void four(View view){
        if(eqPressed){
            textView.setText("4");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "4");
        }
        eqPressed = false;
    }

    public void five(View view){
        if(eqPressed){
            textView.setText("5");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "5");
        }
        eqPressed = false;
    }

    public void six(View view){
        if(eqPressed){
            textView.setText("6");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "6");
        }
        eqPressed = false;
    }

    public void seven(View view){
        if(eqPressed){
            textView.setText("7");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "7");
        }
        eqPressed = false;
    }

    public void eight(View view){
        if(eqPressed){
            textView.setText("8");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "8");
        }
        eqPressed = false;
    }

    public void nine(View view){
        if(eqPressed){
            textView.setText("9");
            result = "";
        }else {
            String string = (String) textView.getText();
            textView.setText(string + "9");
        }
        eqPressed = false;
    }

    public void dot (View view){
        if(eqPressed){
            textView.setText(".");
            result = "";
            eqPressed = false;
            return;
        }

        String string = (String) textView.getText();
        int x = 0;
        if(firstNum.isEmpty()) {
            x = string.length() - string.replace(".", "").length();
        }else{
            String s = string.substring(string.indexOf("\n"));
            x = s.length() - s.replace(".","").length();
        }

        if(x == 0) {
            textView.setText(string + ".");
        }
    }

    public void plusBtn (View view){
        String string = (String) textView.getText();
        if(!string.equalsIgnoreCase("UNDEFINED")) {
            eqPressed = false;
            next = "+";
            update();
        }
    }

    public void minusBtn (View view){
        String string = (String) textView.getText();
        if(!string.equalsIgnoreCase("UNDEFINED")) {
            eqPressed = false;
            next = "-";
            update();
        }
    }

    public void multiplyBtn (View view){
        String string = (String) textView.getText();
        if(!string.equalsIgnoreCase("UNDEFINED")) {
            eqPressed = false;
            next = "*";
            update();
        }
    }

    public void divideBtn (View view){
        String string = (String) textView.getText();
        if(!string.equalsIgnoreCase("UNDEFINED")) {
            eqPressed = false;
            next = "/";
            update();
        }
    }

    public void clear (View view){
        firstNum = "";
        secondNum = "";
        operator = "";
        result = "";
        fixed = "";
        textView.setText("");
    }

    public void backSpace (View view){
        String string = (String)textView.getText();
        int length = string.length();
        if(fixed.length() != length && !eqPressed){
           textView.setText(string.substring(0,length-1));
        }
    }

    public void negative (View view){
        String string = (String) textView.getText();
        if(string.isEmpty()){
            textView.setText("-");
        } else if(string.equalsIgnoreCase(result)){
            if(string.startsWith("-")){
                result = result.replace("-","");
            }else{
                result = "-"+result;
            }
            textView.setText(result);
        } else if(firstNum.isEmpty()){
            if(string.startsWith("-")){
                textView.setText(string.replace("-", ""));
            }else{
                textView.setText("-"+string);
            }
        }else{
            String s = string.replace(fixed, "");
            if(s.isEmpty()){
                textView.setText(fixed+"-");
            }else{
                if(s.startsWith("-")){
                    textView.setText(fixed+s.replace("-", ""));
                }else{
                    textView.setText(fixed+"-"+s);
                }
            }
        }
    }
}
