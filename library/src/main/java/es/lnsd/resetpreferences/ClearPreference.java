package es.lnsd.resetpreferences;

import android.content.Context;
import android.util.AttributeSet;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 */
public class ClearPreference extends ClearablePreference {

    //region Constructors
    public ClearPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ClearPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ClearPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearPreference(Context context) {
        this(context, null);
    }
    //endregion

    @Override
    protected void onClick() {
        removePreference();
    }
}
