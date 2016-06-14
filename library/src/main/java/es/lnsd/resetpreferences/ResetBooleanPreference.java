package es.lnsd.resetpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;

import es.lnsd.resetpreference.R;

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

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ResetBooleanPreference, defStyleAttr, defStyleRes);

        if (attributes != null) {
            defaultValue = attributes.getBoolean(R.styleable.ResetBooleanPreference_resetBooleanValue, false);
            attributes.recycle();
        }
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ResetBooleanPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResetBooleanPreference(Context context) {
        this(context, null);
    }
    //endregion

    @Override
    protected void onClick() {
    persistBoolean(defaultValue);
    }
}
