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
public class ResetStringPreference extends Preference {

    private String defaultValue;

    //region Constructors
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResetStringPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ResetStringPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ResetStringPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResetStringPreference(Context context) {
        super(context);
    }
    //endregion

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        defaultValue = a.getString(index);
        return defaultValue;
    }

    @Override
    protected void onClick() {
        persistString(defaultValue);
    }
}
