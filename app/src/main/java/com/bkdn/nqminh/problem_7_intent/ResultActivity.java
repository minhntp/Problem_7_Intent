package com.bkdn.nqminh.problem_7_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {

    int a, b, c;

    EditText editKq;
    Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        editKq = (EditText) findViewById(R.id.edtKq);
        buttonReturn = (Button) findViewById((R.id.btnReturn));

        giaiPtBacHai();

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bunleHeSo = new Bundle();
                bunleHeSo.putInt("a", a);
                bunleHeSo.putInt("b", b);
                bunleHeSo.putInt("c", c);
                intent.putExtra("HeSo", bunleHeSo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    private void giaiPtBacHai() {
        Intent intentHeSo = getIntent();
        Bundle bundleHeSo = intentHeSo.getBundleExtra("HeSo");

        a = bundleHeSo.getInt("a");
        b = bundleHeSo.getInt("b");
        c = bundleHeSo.getInt("c");

        if(a == 0) {
            if(b == 0) {
                if(c != 0) {
                    editKq.setText("Phương trình vô nghiệm");
                } else {
                    editKq.setText("Phương trình có vô số nghiệm");
                }
            } else {
                editKq.setText("Nghiệm đơn: " + (-c/b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                editKq.setText("Phương trình vô nghiệm");
            } else if(delta == 0){
                editKq.setText("Nghiệm kép: " + (-b/(2*a)));
            } else {
                editKq.setText("x1 = " + ((-b + Math.sqrt(delta)) / (2*a))
                        + "\nx2 = " + ((-b - Math.sqrt(delta)) / (2*a)));
            }
        }
    }
}
