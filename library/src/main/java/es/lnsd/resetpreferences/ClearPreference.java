package es.lnsd.resetpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.Toast;

import es.lnsd.resetpreference.R;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 */
public class ClearPreference extends Preference {

    private String toastText = "";

    //region Constructors
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(context, attrs);
    }

    public ClearPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(context, attrs);
    }

    public ClearPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public ClearPreference(Context context) {
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
    protected void onClick() {
        removePreference();

        if (toastText != null && !toastText.isEmpty()) {
            Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Attempts to remove a preference value from {@link SharedPreferences}.
     * <p>
     * This will check if this Preference is persistent, get an editor from
     * the {@link PreferenceManager}, remove the preference value, and check and commit.
     */
    private void removePreference() {
        if (shouldPersist()) {
            SharedPreferences.Editor editor = getEditor();
            editor.remove(getKey());

            try {
                editor.apply();
            } catch (AbstractMethodError unused) {
                // The app injected its own pre-Gingerbread
                // SharedPreferences.Editor implementation without
                // an apply method.
                editor.commit();
            }
        }
    }
}
