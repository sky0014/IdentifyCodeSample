package com.sky.identifycodesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sky.support.identifycode.IdentifyCodeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IdentifyCodeView codeView = ((IdentifyCodeView) findViewById(R.id.codeView));
        final EditText codeInput = ((EditText) findViewById(R.id.codeInput));
        Button checkButton = ((Button) findViewById(R.id.checkButton));

        codeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regenerate
                codeView.generateCode();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, codeView.verifyCode(codeInput.getText().toString()) ? "Verify Success!" : "Verify Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
