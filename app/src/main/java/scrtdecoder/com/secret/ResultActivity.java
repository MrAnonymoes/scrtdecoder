package scrtdecoder.com.secret;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        BaseClass.activeActivities++;

        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText(InputEncryptedText.progressResult);

        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("progressResult", InputEncryptedText.progressResult);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(ResultActivity.this, "Data gekopieerd naar het clipboard", Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            Log.d("MyLog", "ResultActivity :: try copying to clipboard failed, error: " + e.getLocalizedMessage());
            Toast.makeText(ResultActivity.this, "Fout: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
