package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.learn_thing.learnthingandroid.Activity.MainActivity;
import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 27.05.2016.
 */
public class AlarmReciever extends BroadcastReceiver {
    public static int notificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.notification)
                .setAutoCancel(true)
                .setContentTitle(intent.getStringExtra("title"))
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setContentText(intent.getStringExtra("text"));
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManagerCompat.from(context).notify(notificationId, notification);
        notificationId++;
    }
}
