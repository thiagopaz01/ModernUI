package br.com.thiagopaz.modernui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    private String TAG = "MAIN_ACTIVITY";
    private SeekBar seekBar;
    private View rectRed;
    private View rectBlue;
    private View rectBrown;
    //private View rectWhite;
    private View rectOrange;
    private View rectPurple;
    private View rectGreen;
    private View rectYellow;

    private int colorRed;
    private int colorBlue;
    private int colorGreen;
    private int colorYellow;
    private int colorBrown;
    private int colorOrange;
    private int colorPurple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rectBrown = findViewById(R.id.rectBrown);
        rectRed = findViewById(R.id.rectRed);
        rectBlue = findViewById(R.id.rectBlue);
        //rectWhite = findViewById(R.id.rectWhite);
        rectOrange = findViewById(R.id.rectOrange);
        rectPurple = findViewById(R.id.rectPurple);
        rectGreen = findViewById(R.id.rectGreen);
        rectYellow = findViewById(R.id.rectYellow);

        colorRed = Color.parseColor("#ff0000");
        colorBlue = Color.parseColor("#0000ff");
        colorGreen = Color.parseColor("#006700");
        colorYellow = Color.parseColor("#FFFF00");
        colorBrown = Color.parseColor("#a5682a");
        colorOrange = Color.parseColor("#ffa500");
        colorPurple = Color.parseColor("#800080");

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                modifyColor(rectRed,colorRed,progress);
                modifyColor(rectBlue,colorBlue,progress);
                modifyColor(rectGreen,colorGreen,progress);
                modifyColor(rectYellow,colorYellow,progress);
                modifyColor(rectBrown,colorBrown,progress);
                modifyColor(rectOrange,colorOrange,progress);
                modifyColor(rectPurple,colorPurple,progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void modifyColor(View rect, int color, int progress) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        /*if(progress > 70) {
            red = (int) (255 - Math.abs(255 * Math.cos(Math.toRadians(1.8*40 + (Math.acos(Math.toRadians(red/255)))))));
            green = (int) (255 - Math.abs(255 * Math.cos(Math.toRadians(1.8*70 + (Math.acos(Math.toRadians(green/255)))))));
            blue = (int) (255 - Math.abs(255 * Math.cos(Math.toRadians(1.8*progress + (Math.acos(Math.toRadians(blue/255)))))));
        }
        else if(progress > 40) {
            red = (int) (255 - Math.abs(255 * Math.cos(Math.toRadians(1.8*40 + (Math.acos(Math.toRadians(red/255)))))));
            green = (int) (255 - Math.abs(255 * Math.cos(Math.toRadians(1.8*progress + (Math.acos(Math.toRadians(green/255)))))));
        }
        else {
            red = (int) (20 + Math.abs(220 * Math.sin(Math.toRadians(1.8*progress))));
        }
*/


        //Log.d(TAG, String.valueOf(red));
        red -= progress;
        green += progress;
        blue += 2*(progress);

        if(green > 255)
            green -= 255;
        else if(green < 0)
            green = 255 - green;
        if(red > 255)
            red -= 255;
        else if(red < 0)
            red = 255 - red;
        if(blue > 255)
            blue -= 255;
        else if(blue < 0)
            blue = 255 - blue;

        rect.setBackgroundColor(Color.rgb(red,green,blue));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_more_information) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_text);
            // Add the buttons
            builder.setPositiveButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                    startActivity(browserIntent);
                }
            });
            builder.setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
