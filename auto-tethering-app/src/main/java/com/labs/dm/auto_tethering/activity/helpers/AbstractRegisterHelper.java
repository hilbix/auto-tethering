package com.labs.dm.auto_tethering.activity.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import com.labs.dm.auto_tethering.activity.MainActivity;
import com.labs.dm.auto_tethering.db.DBManager;

/**
 * Created by Daniel Mroczka on 9/21/2016.
 */
abstract class AbstractRegisterHelper {
    protected final MainActivity activity;
    protected final SharedPreferences prefs;
    protected final DBManager db;

    protected AbstractRegisterHelper(MainActivity activity) {
        this.activity = activity;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        this.db = DBManager.getInstance(activity);
    }

    //public abstract void registerUIListeners();

    class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (min <= input && input <= max || (dest.length() + source.length() < 2)) {
                    return null;
                }
            } catch (NumberFormatException nfe) {
                Log.e("InputFilterMinMax", nfe.getMessage());
            }
            return "";
        }
    }
}
