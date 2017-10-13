package com.leapwill.plainnote;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText plainNoteEditText;
    SharedPreferences prefs;
    String prefsTextKey = "com.leapwill.plainnote.text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.plainNoteEditText = (EditText) findViewById(R.id.plainNoteEditText);
        this.prefs = getSharedPreferences("com.leapwill.plainnote", 0);

        this.plainNoteEditText.setText(this.prefs.getString(this.prefsTextKey, ""));

    }

    public void saveNote (View v) {
        this.prefs.edit().putString(this.prefsTextKey, this.plainNoteEditText.getText().toString()).apply();
        //send intent to widget
        this.finish();
    }
}
