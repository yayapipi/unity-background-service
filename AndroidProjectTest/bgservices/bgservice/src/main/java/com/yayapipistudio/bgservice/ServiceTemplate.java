package com.yayapipistudio.bgservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ServiceTemplate extends Service {

    public static final String ChannelId = "BGServiceChannel";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CreateNotificationChannel();
        StartNotification("Service Title", "Service Content");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            final NotificationChannel notificationChannel = new NotificationChannel(
                    ChannelId,
                    "Background Service Template",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void StartNotification(String Title, String Content){
        Intent notificationIntent = new Intent(this, BGServiceInstance.UnityActivity.getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, ChannelId)
                .setContentTitle(Title)
                .setContentText(Content)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(112, notification);
    }
}
