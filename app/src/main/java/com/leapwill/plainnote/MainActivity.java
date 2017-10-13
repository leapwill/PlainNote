package com.leapwill.plainnote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText plainNoteEditText;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.plainNoteEditText = (EditText) findViewById(R.id.plainNoteEditText);
        this.prefs = getSharedPreferences("com.leapwill.plainnote", 0);

        this.plainNoteEditText.setText(this.prefs.getString("com.leapwill.plainnote.text", ""));

    }

    public void saveNote (View v) {
        this.prefs.edit().putString("com.leapwill.plainnote.text", this.plainNoteEditText.getText().toString()).apply();
        this.updateWidget();
        this.finish();
    }

    public void updateWidget() {
        Intent intent = new Intent(this, WidgetProvider.class);
        intent.putExtra("TEXT_STRING", this.plainNoteEditText.getText().toString());
        sendBroadcast(intent);
    }
}
