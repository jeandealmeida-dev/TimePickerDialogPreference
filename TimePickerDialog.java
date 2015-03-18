package br.com.timerjob.base;

import android.content.Context;
import android.preference.DialogPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

import hirondelle.date4j.DateTime;

/**
 * Created by jean.almeida on 18/03/2015.
 */
public class TimePickerDialog extends DialogPreference {

    TimePicker timePicker;
    private String time;

    public TimePickerDialog(Context context) {
        this(context, null);
    }

    public TimePickerDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimePickerDialog(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.timePicker = new TimePicker(context, attrs);

        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }

    public void setTime(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        time = text;

        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    public String getTime() {
        return time;
    }

    public String getCurrentTime() {

        timePicker.getCurrentHour();

        return timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute();
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        DateTime time = new DateTime(this.time);
        timePicker.setCurrentHour(time.getHour());
        timePicker.setCurrentMinute(time.getMinute());
    }

    @Override
    public View onCreateDialogView(){
        timePicker =  new TimePicker(getContext());
        timePicker.setIs24HourView(true);
        return timePicker;
    }

    public void onDialogClosed(boolean positiveResult){
        if (positiveResult) {
            String value = getCurrentTime();
            if (callChangeListener(value)) {
                setTime(value);
            }
        }
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setTime(restoreValue ? getPersistedString(time) : (String) defaultValue);
    }

    @Override
    public boolean shouldDisableDependents() {
        return TextUtils.isEmpty(time) || super.shouldDisableDependents();
    }

}
