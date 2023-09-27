package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String input = "", answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }
    public void clickBtn(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){
            case "C":
                if(input.length() > 0){
                    String newText = input.substring(0, input.length()-1);
                    input = newText;
                }
                break;
            case "AC":
                input = "";
                break;
            case "=":
                solve();
                answer = input;
                break;
            case "x":
                if (input.length()>0){
                    solve();
                    input += "*";
                }else {
                    toast("Invalid format used.");
                }
                break;
            default:
                if (data.equals("+") || data.equals("-") || data.equals("/")){
                    if (input.length() > 0){
                        solve();
                        input += data;
                    }
                    else {
                        toast("Invalid format used.");
                    }
                }else {
                    input += data;
                }
        }
        textView.setText(input);
    }

    public void solve(){
        if (input.split("\\*").length==2){
            String num[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(num[0]) * Double.parseDouble(num[1]);
                input = String.valueOf(mul);
            }catch (Exception ex){
//                toast("Error detected.");
            }
        }
        if (input.split("\\/").length==2){
            String num[] = input.split("\\/");
            try {
                if(Double.parseDouble((num[1])) != 0){
                    double div = Double.parseDouble(num[0]) / Double.parseDouble(num[1]);
                    input = div + "";
                }else {
                    toast("Could not divide by 0.");
                }
            }catch (NumberFormatException ex){
                toast("Error detected." + ex.getMessage());
            }
        }
        if (input.split("\\+").length==2){
            String num[] = input.split("\\+");
            try {
                double sum = Double.parseDouble(num[0]) + Double.parseDouble(num[1]);
                input = sum + "";
            }catch (Exception ex){
//                toast("Error detected.");
            }
        }
        if (input.split("\\-").length==2){
            String num[] = input.split("\\-");
            try {
                double sub = Double.parseDouble(num[0]) - Double.parseDouble(num[1]);
                input = sub + "";
            }catch (Exception ex){
//                toast("Error detected.");
            }
        }
    }
    public void toast(String content){
        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}