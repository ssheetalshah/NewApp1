package com.ics.newapp;

import android.app.AlertDialog;
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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    TextView addRemin;
    Toolbar toolbar_prof;
    AlertDialog.Builder builder;

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
        builder = new AlertDialog.Builder(this);

        addRemin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=DAILY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "A Test Event from android app");
                startActivity(intent);
             //   addReminderInCalendar();
                //Uncomment the below code to Set the message and title from the strings.xml file
            //    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

            /*    //Setting message manually and performing action on button click
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               addReminderInCalendar();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();*/
            }
        });
    }

    private static void addToCalendar(Context ctx, final String title, final long dtstart, final long dtend) {
        final ContentResolver cr = ctx.getContentResolver();
        Cursor cursor ;
        if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
            cursor = cr.query(Uri.parse("content://com.android.calendar/calendars"), new String[]{ "_id", "displayname" }, null, null, null);
        else
            cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "displayname" }, null, null, null);
        if ( cursor.moveToFirst() ) {
            final String[] calNames = new String[cursor.getCount()];
            final int[] calIds = new int[cursor.getCount()];
            for (int i = 0; i < calNames.length; i++) {
                calIds[i] = cursor.getInt(0);
                calNames[i] = cursor.getString(1);
                cursor.moveToNext();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setSingleChoiceItems(calNames, -1, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentValues cv = new ContentValues();
                    cv.put("calendar_id", calIds[which]);
                    cv.put("title", title);
                    cv.put("dtstart", dtstart );
                    cv.put("hasAlarm", 1);
                    cv.put("dtend", dtend);

                    Uri newEvent ;
                    if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
                        newEvent = cr.insert(Uri.parse("content://com.android.calendar/events"), cv);
                    else
                        newEvent = cr.insert(Uri.parse("content://calendar/events"), cv);

                    if (newEvent != null) {
                        long id = Long.parseLong( newEvent.getLastPathSegment() );
                        ContentValues values = new ContentValues();
                        values.put( "event_id", id );
                        values.put( "method", 1 );
                        values.put( "minutes", 15 ); // 15 minutes
                        if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
                            cr.insert( Uri.parse( "content://com.android.calendar/reminders" ), values );
                        else
                            cr.insert( Uri.parse( "content://calendar/reminders" ), values );

                    }
                    dialog.cancel();
                }

            });

            builder.create().show();
        }
        cursor.close();
    }
}
