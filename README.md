# TimePickerDialogPreference
This is a component to pick the time from dialog in preference activity. 

How to use:

To use that, download the class "TimePickerDialog" and add in your project. In your PreferenceScreen add a PreferenceCategory like the example: 

    <PreferenceCategory android:title="Schedule" >
        <YOUR PACKAGE.TimePickerDialog
          android:defaultValue="08:00"
          android:key="schedule_start_time"
          android:showDefault="true"
          android:summary="When to start"
          android:title="Start time" />
    </PreferenceCategory>

Based in question: 
http://stackoverflow.com/questions/17403218/
