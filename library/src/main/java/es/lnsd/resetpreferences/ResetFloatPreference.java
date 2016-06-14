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
public class ResetFloatPreference extends Preference {

    private float defaultValue;

    //region Constructors
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResetFloatPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ResetFloatPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ResetFloatPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResetFloatPreference(Context context) {
        super(context);
    }
    //endregion

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        defaultValue = a.getFloat(index, -1f);
        return defaultValue;
    }

    @Override
    protected void onClick() {
        persistFloat(defaultValue);
    }
}
