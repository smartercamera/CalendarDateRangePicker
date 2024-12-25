package com.archit.calendardaterangepickerdemo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.util.Calendar;

import static com.archit.calendardaterangepicker.customviews.CalendarRangeUtilsKt.printDate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DateRangeCalendarView calendar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.cdrvCalendar);
        calendar.setCalendarListener(calendarListener);

        calendar.setWeekOffset(1);

        findViewById(R.id.btnReset).setOnClickListener(v -> calendar.resetAllSelectedViews());

//        calendar.setNavLeftImage(ContextCompat.getDrawable(this,R.drawable.ic_left));
//        calendar.setNavRightImage(ContextCompat.getDrawable(this,R.drawable.ic_right));

        final Calendar startMonth = Calendar.getInstance();
        startMonth.set(Calendar.MONTH, startMonth.get(Calendar.MONTH) - 1);
        final Calendar endMonth = Calendar.getInstance();
        Log.d(TAG, "Start month: " + startMonth.getTime().toString() + " :: End month: " + endMonth.getTime().toString());
        calendar.setVisibleMonthRange(startMonth, endMonth);

        final Calendar startDateSelectable = (Calendar) startMonth.clone();
        final Calendar endDateSelectable = (Calendar) endMonth.clone();
        Log.d(TAG, "startDateSelectable: " + startDateSelectable.getTime().toString() + " :: endDateSelectable: " + endDateSelectable.getTime().toString());
        calendar.setSelectableDateRange(startDateSelectable, endDateSelectable);
        calendar.setCurrentMonth(endMonth);
    }

    private final CalendarListener calendarListener = new CalendarListener() {
        @Override
        public void onFirstDateSelected(@NonNull final Calendar startDate) {
            Toast.makeText(MainActivity.this, "Start Date: " + startDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Selected dates: Start: " + printDate(calendar.getStartDate()) +
                    " End:" + printDate(calendar.getEndDate()));
        }

        @Override
        public void onDateRangeSelected(@NonNull final Calendar startDate, @NonNull final Calendar endDate) {
            Toast.makeText(MainActivity.this, "Start Date: " + startDate.getTime().toString() + " End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Selected dates: Start: " + printDate(calendar.getStartDate()) +
                    " End:" + printDate(calendar.getEndDate()));
        }
    };
}
