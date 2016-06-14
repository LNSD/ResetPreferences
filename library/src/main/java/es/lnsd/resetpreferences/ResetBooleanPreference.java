package es.lnsd.resetpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 */
public class ResetBooleanPreference extends Preference {

    private boolean defaultValue;

    //region Constructors
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResetBooleanPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResetBooleanPreference(Context context) {
        super(context);
    }
    //endregion

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        defaultValue = a.getBoolean(index, true);
        return defaultValue;
    }

    @Override
    protected void onClick() {
        persistBoolean(defaultValue);
    }
}
