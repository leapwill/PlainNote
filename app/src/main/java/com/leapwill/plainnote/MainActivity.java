package com.leapwill.plainnote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText plainNoteEditText;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.plainNoteEditText = findViewById(R.id.plainNoteEditText);
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //bring text from old preferences file to default
        String oldNote = getSharedPreferences("com.leapwill.plainnote", 0).getString("com.leapwill.plainnote.text", null);
        if (oldNote != null) {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("plainNoteText", oldNote).commit();
            getSharedPreferences("com.leapwill.plainnote", 0).edit().putString("com.leapwill.plainnote.text", null).apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.plainNoteEditText.setText(this.prefs.getString("plainNoteText", ""));
        this.plainNoteEditText.setBackgroundColor(this.prefs.getInt("plainNotePrefBGColor", 0xdd222222));
        this.plainNoteEditText.setTextColor(this.prefs.getInt("plainNotePrefTextColor", 0xffffffff));
    }

    public void saveNote (View v) {
        this.prefs.edit().putString("plainNoteText", this.plainNoteEditText.getText().toString()).apply();
        this.updateWidget();
        this.finish();
    }

    public void updateWidget() {
        Intent intent = new Intent(this, WidgetProvider.class);
        intent.putExtra("TEXT_STRING", this.plainNoteEditText.getText().toString());
        sendBroadcast(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}