package es.lnsd.example.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.lnsd.example.R;
import es.lnsd.example.settings.SettingsActivity;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0x00;

    //region Views
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.boolean_switch)
    Switch booleanInput;
    @Bind(R.id.boolean_value)
    TextView booleanValue;
    @Bind(R.id.integer_input)
    EditText integerInput;
    @Bind(R.id.integer_value)
    TextView integerValue;
    @Bind(R.id.float_input)
    EditText floatInput;
    @Bind(R.id.float_value)
    TextView floatValue;
    @Bind(R.id.string_input)
    EditText stringInput;
    @Bind(R.id.string_value)
    TextView stringValue;
    //endregion

    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        refreshTextViewValues();
    }

    //region Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivityForResult(intent, SettingsActivity.REQUEST_CODE);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    @OnClick(R.id.apply_button)
    public void onApplyButtonClick(Button button) {
        updateStoredValues();
        refreshTextViewValues();
        clearEditText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SettingsActivity.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    refreshTextViewValues();
                }
                break;
            default:
                //Do nothing
        }
    }

    private void refreshTextViewValues() {

        if (!sharedPreferences.contains(getString(R.string.boolean_key))) {
            booleanValue.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            booleanValue.setText(getString(R.string.not_set_value));
        } else {
            booleanValue.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            boolean valueBoolean = sharedPreferences.getBoolean(getString(R.string.boolean_key), false);
            booleanValue.setText((valueBoolean) ? "true" : "false");
            booleanInput.setChecked(valueBoolean);
        }

        if (!sharedPreferences.contains(getString(R.string.integer_key))) {
            integerValue.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            integerValue.setText(getString(R.string.not_set_value));
        } else {
            integerValue.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            int valueInteger = sharedPreferences.getInt(getString(R.string.integer_key), -1);
            integerValue.setText(Integer.toString(valueInteger));
        }

        if (!sharedPreferences.contains(getString(R.string.float_key))) {
            floatValue.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            floatValue.setText(getString(R.string.not_set_value));
        } else {
            floatValue.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            float valueFloat = sharedPreferences.getFloat(getString(R.string.float_key), -1f);
            floatValue.setText(Float.toString(valueFloat));
        }

        if (!sharedPreferences.contains(getString(R.string.string_key))) {
            stringValue.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            stringValue.setText(getString(R.string.not_set_value));
        } else {
            stringValue.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            String valueString = sharedPreferences.getString(getString(R.string.string_key), null);
            stringValue.setText(valueString);
        }

        Timber.d("TextView values refreshed.");
    }

    private void updateStoredValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(getString(R.string.boolean_key), booleanInput.isChecked());

        String istring = integerInput.getText().toString();
        if (!istring.isEmpty()) {
            editor.putInt(getString(R.string.integer_key), Integer.parseInt(istring));
        }

        String fstring = floatInput.getText().toString();
        if (!fstring.isEmpty()) {
            editor.putFloat(getString(R.string.float_key), Float.parseFloat(fstring));
        }

        String string = stringInput.getText().toString();
        if (!string.isEmpty()) {
            editor.putString(getString(R.string.string_key), string);
        }

        editor.apply();
    }

    private void clearEditText() {
        integerInput.setText("");
        floatInput.setText("");
        stringInput.setText("");
    }
}
