package scrtdecoder.com.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

public class InputEncryptedText extends BaseClass {
    public static String progressResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_encrypted_text);
        BaseClass.activeActivities++;

        String codeWordSetText = Homescreen.CODE_WORD;
        TextView showCurrentCodeWordTV = findViewById(R.id.showCurrentCodeWordTV);
        showCurrentCodeWordTV.setText(codeWordSetText);

        EditText decodeText = findViewById(R.id.decodeText);
        decodeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                    hideKeyboard(view);
            }
        });

        EditText encodeText = findViewById(R.id.encodeText);
        encodeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                    hideKeyboard(view);
            }
        });

        Button btnEncode = findViewById(R.id.btnEncode);
        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText encodeTextET = findViewById(R.id.encodeText);
                Encode encoder = new Encode();
                encoder.putText(encodeTextET.getText().toString());
                progressResult = encoder.encode();
                Intent resultActivity = new Intent(InputEncryptedText.this, ResultActivity.class);
                startActivity(resultActivity);
            }
        });

        Button btnDecode = findViewById(R.id.btnDecode);
        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText decodeTextET = findViewById(R.id.decodeText);
                Decode decoder = new Decode();
                decoder.putText(decodeTextET.getText().toString());
                progressResult = decoder.decode();
                Intent resultActivity = new Intent(InputEncryptedText.this, ResultActivity.class);
                startActivity(resultActivity);
            }
        });

        FloatingActionButton fabEditCodeWord = findViewById(R.id.fabEditCodeWord);
        fabEditCodeWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputCodeWordIntent = new Intent(InputEncryptedText.this, InputCodeWord.class);
                startActivity(inputCodeWordIntent);
            }
        });

    }

    public void hideKeyboard(View view) {
        Log.d("MyLog", "hideKeyboard");
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }}
