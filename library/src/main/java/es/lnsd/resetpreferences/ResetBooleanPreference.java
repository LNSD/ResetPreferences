package es.lnsd.resetpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.Toast;

import es.lnsd.resetpreference.R;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 */
public class ResetBooleanPreference extends Preference {

    private boolean defaultValue;
    private String toastText = "";

    //region Constructors
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResetBooleanPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(context, attrs);
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(context, attrs);
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public ResetBooleanPreference(Context context) {
        this(context, null);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ResetPreference);
        toastText = typedArray.getString(R.styleable.ResetPreference_toastText);
        typedArray.recycle();
    }
    //endregion

    public void setToastText(String toastText) {
        this.toastText = toastText;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        defaultValue = a.getBoolean(index, true);
        return defaultValue;
    }

    @Override
    protected void onClick() {
        persistBoolean(defaultValue);

        if (!toastText.isEmpty()) {
            Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT).show();
        }
    }
}
