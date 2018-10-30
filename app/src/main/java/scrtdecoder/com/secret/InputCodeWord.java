package scrtdecoder.com.secret;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InputCodeWord extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_code_word);
        BaseClass.activeActivities++;
        // create prefs object with value 'general_settings' to get all general settings values with keys included
        final SharedPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);


        // set current code word into textview currentCodeWordTV
        TextView currentCodeWordTV = findViewById(R.id.currentCodeWordTV);
        String currentCodeWordTVText = "Huidige codewoord is: " + Homescreen.CODE_WORD;
        currentCodeWordTV.setText(currentCodeWordTVText);

        // Declare button to handle his action
        Button saveCodeWord = findViewById(R.id.btnSaveCodeWord);

        // get value of input
        final EditText inputCodeWordET = findViewById(R.id.inputCodeWord);
        saveCodeWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Overwrite current codeWord with new one
                String inputCodeWord = inputCodeWordET.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("codeWord", inputCodeWord);
                editor.apply();

                Homescreen.CODE_WORD = inputCodeWord;

                // Check if codeword really changed and handle errors, if success, start new activity InputEncryptedText
                if (prefs.getString("codeWord", null).equals(inputCodeWord)) {
                    Toast.makeText(InputCodeWord.this, "Opgeslagen", Toast.LENGTH_LONG).show();
                    startIntentInputEncryptedText();

                    //Log
                    Log.d("MyLog", "Success: prefs.getstring('codeword') overwritten by codeword: " + inputCodeWord);
                } else
                    Toast.makeText(InputCodeWord.this, "Er is een fout opgetreden, ren ff naar Pedro", Toast.LENGTH_LONG).show();

                // Log
                Log.d("MyLog", "Error: saveCodeWord.onClickListener :: else -> codeword != inserted text after save: Saving codeword does not work?");
                Log.d("MyLog", "Inserted word: " + inputCodeWord + ", current saved codeword: " + prefs.getString("codeWord", null));
            }
        });

        // hide keyboard if input lost focus
        inputCodeWordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus)
                    hideKeyboard(view);
            }
        });

    }

    public void startIntentInputEncryptedText()
    {
        Intent inputEncryptedText = new Intent(InputCodeWord.this, InputEncryptedText.class);
        startActivity(inputEncryptedText);
        finish();
        BaseClass.activeActivities--;
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
