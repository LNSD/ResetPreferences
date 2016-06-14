package es.lnsd.resetpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 */
public class ClearablePreference extends Preference {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearablePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ClearablePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClearablePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearablePreference(Context context) {
        super(context);
    }

    /**
     * Attempts to remove a preference value from {@link SharedPreferences}.
     * <p>
     * This will check if this Preference is persistent, get an editor from
     * the {@link PreferenceManager}, remove the preference value, and check and commit.
     *
     * @return True if the Preference was removed.
     */
    protected boolean removePreference() {
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
            return true;
        }
        return false;
    }
}
