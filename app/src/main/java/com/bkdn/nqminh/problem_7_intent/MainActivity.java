package com.bkdn.nqminh.problem_7_intent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB, editC;
    Button buttonKq, buttonReturn;

    static final int REQ_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editA = (EditText) findViewById(R.id.edtA);
        editB = (EditText) findViewById(R.id.edtB);
        editC = (EditText) findViewById(R.id.edtC);
        buttonKq = (Button) findViewById(R.id.btnKq);
        buttonReturn = (Button) findViewById(R.id.btnReturn);

        buttonKq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0, b = 0, c = 0;
                int error = 0;
                try {
                    a = Integer.parseInt(editA.getText().toString());
                } catch(Exception e) {
                    editA.setText("");
                    editA.setHint("Lỗi định dạng số");
                    error = 1;
                }
                try {
                    b = Integer.parseInt(editB.getText().toString());
                } catch (Exception e) {
                    editB.setText("");
                    editB.setHint("Lỗi định dạng số");
                    error = 1;
                }
                try {
                    c = Integer.parseInt(editC.getText().toString());
                } catch (Exception e) {
                    editC.setText("");
                    editC.setHint("Lỗi định dạng số");
                    error = 1;
                }
                if(error == 1) {
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundleHeSo = new Bundle();
                bundleHeSo.putInt("a",a);
                bundleHeSo.putInt("b",b);
                bundleHeSo.putInt("c",c);
                intent.putExtra("HeSo", bundleHeSo);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == REQ_CODE) {
            Bundle bundleHeSo = intent.getBundleExtra("HeSo");
            int a = bundleHeSo.getInt("a");
            int b = bundleHeSo.getInt("b");
            int c = bundleHeSo.getInt("c");
            String text = "Welcome back!!!\nHệ số a, b, c vừa rồi là: " + a + "; " + b + "; " + c;
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            editA.setText("");
            editB.setText("");
            editC.setText("");
            editA.setHint("");
            editB.setHint("");
            editC.setHint("");
            editA.requestFocus();
        }
    }

}
