package scrtdecoder.com.secret;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Homescreen extends BaseClass {

    public static String CODE_WORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        BaseClass.activeActivities++;

        SharedPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        String codeWord = prefs.getString("codeWord", null);
        if(codeWord == null) {
            Log.d("MyLog", "codeWord is empty");
            final Handler delay = new Handler();
            delay.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent InputCodeWord = new Intent(Homescreen.this, scrtdecoder.com.secret.InputCodeWord.class);
                    startActivity(InputCodeWord);
                    finish();
                    BaseClass.activeActivities--;
                }
            }, 4000);
        }
        else {
            CODE_WORD = codeWord;
            Log.d("MyLog", "codeWord contains: " + codeWord);
            final Handler delay = new Handler();
            delay.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent InputEncryptedText = new Intent(Homescreen.this, scrtdecoder.com.secret.InputEncryptedText.class);
                    startActivity(InputEncryptedText);
                    finish();
                    BaseClass.activeActivities--;
                }
            }, 4000);

        }

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
