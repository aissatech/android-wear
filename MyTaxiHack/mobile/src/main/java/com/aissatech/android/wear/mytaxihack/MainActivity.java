package com.aissatech.android.wear.mytaxihack;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {//ActionBarActivity deprecated

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
        wearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationId = 001;

            // Get an instance of the NotificationManager service
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

            // multi page notification
            // Create builder for the main notification (first page)
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.new_message)
                                .setContentTitle("Page 1")
                                .setContentText("Sto arrivando in taxi");

            // Create a big text style for the second page
                NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
                secondPageStyle
                        .setBigContentTitle("Page 2")
                        .bigText("Tra un quarto d'ora sono a casa");

            // Create second page notification
                Notification secondPageNotification = new NotificationCompat.Builder(MainActivity.this)
                                .setStyle(secondPageStyle)
                                .build();

            // add action: build an intent for an action to view a map on the third page
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                Uri geoUri = Uri.parse("geo:<41.887050>,<12.504496>?q=<41.887050>,<12.504496>(Piazza San Giovanni)");
                mapIntent.setData(geoUri);
                PendingIntent mapPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, mapIntent, 0);

            //  add action: build an intent for an action to call a smartphone number
                String number = "+391234567890";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: " + number));
                PendingIntent callPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, callIntent, 0);

            // add action: build an intent for an action to send email
                String uriText =
                        "mailto:info@email.com" +
                                "?subject=" + Uri.encode("Arrivo in taxi") +
                                "&body=" + Uri.encode("Sono dalle parti di San Giovanni, sto arrivando! Camelia");
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                PendingIntent sendPendingIntent = PendingIntent.getActivity(MainActivity.this, 1, sendIntent, 0);

            // Create a big text style for the third page
                NotificationCompat.BigTextStyle thirdPageStyle = new NotificationCompat.BigTextStyle();
                thirdPageStyle
                        .setBigContentTitle("Page 3")
                        .bigText("Per momento sono qua: ");

            // set style and build the third page notification
                Notification thirdPageNotification = new NotificationCompat.Builder(MainActivity.this)
                    .setStyle(thirdPageStyle)
                    .build();

            // third page with intent builder
                notificationBuilder
                        .setSmallIcon(R.drawable.ic_event)
                        .setContentIntent(mapPendingIntent)
                        .addAction(R.drawable.ic_map, getString(R.string.map), mapPendingIntent)
                        .addAction(R.drawable.ic_call, getString(R.string.call), callPendingIntent)
                        .addAction(R.drawable.ic_send, getString(R.string.send), sendPendingIntent);

            // create notification, adding the second and the third page
                Notification notification = notificationBuilder
                        .extend(new NotificationCompat.WearableExtender()
                        .addPage(secondPageNotification)
                        .addPage(thirdPageNotification))
                        .build();
            // send the notification
                notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId, notification);


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
