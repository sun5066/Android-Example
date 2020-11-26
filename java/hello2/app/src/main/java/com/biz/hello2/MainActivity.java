package com.biz.hello2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView txtView1;
    private TextView txtView2;
    private Button btnClick1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView1 = findViewById(R.id.txtView1);
        txtView2 = findViewById(R.id.txtView2);
        btnClick1 = findViewById(R.id.btnClick);

        txtView1.setText("나는 TextView1 입니다.");
        txtView2.setText("나는 TextView2 입니다.");

        View.OnClickListener btnListener = new BtnClickListener();
        btnClick1.setOnClickListener(btnListener);

        btnClick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "반갑다", Toast.LENGTH_LONG).show();
                txtView2.setText("음 바꿀꺼야~~");
                Snackbar.make(v, "나는 쓰렉바입니다", Snackbar.LENGTH_LONG).show();
            }
        });


        // xml 에 설정된 컴포넌트를 java code에서 사용하기 위해서
        // 연결하는 작업
        Log.d("onCreate", "onCreate: " + txtView1.getText());
    }
}