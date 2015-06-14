package com.aissatech.android.wear.mytaxihack;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity_old extends ActionBarActivity {
//    // Key for the string that's delivered in the action's intent
//    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toast on image
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.taxi_hack);

        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Taxi Hack: 13 - 14 giugno 2015, Sala Conferenze 3570 Via del Casale Lumbroso 167, Roma",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // notification on button
        Button wearButton = (Button) findViewById(R.id.wearButton);
        wearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationId = 001;

                  // simple notification
//                NotificationCompat.Builder notificationBuilder =
//                        new NotificationCompat.Builder(MainActivity.this)
//                                .setSmallIcon(R.drawable.ic_launcher)
//                                .setContentTitle("TaxiHack")
//                                .setContentText("Android Wear Notification");
//
//                // Get an instance of the NotificationManager service
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity_old.this);
//
//                notificationManager.notify(notificationId, notificationBuilder.build());

                // multi page notification
                // Create builder for the main notification
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity_old.this)
                                .setSmallIcon(R.drawable.new_message)
                                .setContentTitle("Page 1")
                                .setContentText("Sto arrivando in taxi");
//                                .setContentIntent(viewPendingIntent);

            // Create a big text style for the second page
                NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
                secondPageStyle.setBigContentTitle("Page 2")
                        .bigText("Tra un quarto d'ora sono a casa");

            // Create second page notification
                Notification secondPageNotification =
                        new NotificationCompat.Builder(MainActivity_old.this)
                                .setStyle(secondPageStyle)
                                .build();

            // Extend the notification builder with the second page
                Notification notification = notificationBuilder
                        .extend(new NotificationCompat.WearableExtender()
                        .addPage(secondPageNotification))
                        .build();

            // Issue the notification
                notificationManager = NotificationManagerCompat.from(MainActivity_old.this);
                notificationManager.notify(notificationId, notification);

//                // add action: build an intent for an action to view a map
//                //int notificationId = 002;
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
//                //Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
//                Uri geoUri = Uri.parse("geo:<lat>,<long>?q=<lat>,<long>(Label+Name)");
//                mapIntent.setData(geoUri);
//                PendingIntent mapPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, mapIntent, 0);
//
//                NotificationCompat.Builder notificationBuilder =
//                        new NotificationCompat.Builder(MainActivity.this)
//                                .setSmallIcon(R.drawable.ic_event)
//                                .setContentTitle("Mappa")
//                                .setContentText("Per momento sono qua: ")
//                                .setContentIntent(mapPendingIntent)
//                                .addAction(R.drawable.ic_map, getString(R.string.map), mapPendingIntent);
//
//                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
//                notificationManager.notify(notificationId, notificationBuilder.build());

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
