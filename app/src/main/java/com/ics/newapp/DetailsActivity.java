package com.ics.newapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    TextView addRemin;
    Toolbar toolbar_prof;
    AlertDialog.Builder builder;
    Calendar myCalendar;
    ProgressDialog dialog;
    DatePickerDialog.OnDateSetListener date;
    private String dateFlage;
    EditText dtdg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar_prof = (Toolbar) findViewById(R.id.toolbar_prof);
        // Title and subtitle
        // toolbar_prof.setTitle(R.string.about_toolbar_title);
        toolbar_prof.setNavigationIcon(R.drawable.arrow);
        toolbar_prof.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   stopActivityTask();
                onBackPressed();
            }
        });

        addRemin = (TextView)findViewById(R.id.addremin);
        dtdg = (EditText)findViewById(R.id.dtdg);
        builder = new AlertDialog.Builder(this);

        addRemin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setTitle("Add a reminder");
                View dialogLayout = inflater.inflate(R.layout.dialog_reminder, null);
           //     picDate();
               // final RatingBar ratingBar = dialogLayout.findViewById(R.id.ratingBar);
                builder.setView(dialogLayout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void picDate() {

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dtdg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateFlage = "1";
                new DatePickerDialog(DetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {

        if (dateFlage.equalsIgnoreCase("1")) {
            String myFormat = "dd-MM-yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
            dtdg.setText(sdf.format(myCalendar.getTime()));
            dtdg.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
