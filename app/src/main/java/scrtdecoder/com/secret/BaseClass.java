package scrtdecoder.com.secret;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

public class BaseClass extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_black));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.main_logo);
        return super.onCreateOptionsMenu(menu);
    }

    public static int activeActivities = 0;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (activeActivities <= 1) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            activeActivities--;
            super.onBackPressed();
        }
    }

}
