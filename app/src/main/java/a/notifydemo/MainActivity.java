package a.notifydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel("a.notifydemo.news",
                "NotifyDemo News", "Example News Channel");

        final String GROUP_KEY_NOTIFY = "group_key_notify";
        String channelID = "a.notifydemo.news";

        Notification.Builder builderSummary = new Notification.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("A Bundle Example")
                .setContentText("You have 3 new messages")
                .setGroup(GROUP_KEY_NOTIFY)
                .setGroupSummary(true);

        Notification.Builder builder1 = new Notification.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("New Message")
                .setContentText("You have a new message from Kassidy")
                .setGroup(GROUP_KEY_NOTIFY);

        Notification.Builder builder2 = new Notification.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("New Message")
                .setContentText("You have a new message from Caitlyn")
                .setGroup(GROUP_KEY_NOTIFY);

        Notification.Builder builder3 = new Notification.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("New Message")
                .setContentText("You have a new message from Jason")
                .setGroup(GROUP_KEY_NOTIFY);

        int notificationId0 = 100;
        int notificationId1 = 101;
        int notificationId2 = 102;
        int notificationId3 = 103;

        notificationManager.notify(notificationId1, builder1.build());
        notificationManager.notify(notificationId2, builder2.build());
        notificationManager.notify(notificationId3, builder3.build());
        notificationManager.notify(notificationId0, builderSummary.build());
    }

    protected void createNotificationChannel(String id, String name, String description) {
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel(id, name, importance);

        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(channel);
    }

    public void sendNotification(View view) {

        int notificationID = 101;
        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String channelID = "a.notifydemo.news";

        final Icon icon = Icon.createWithResource(MainActivity.this, android.R.drawable.ic_dialog_info);
        Notification.Action action = new Notification.Action.Builder(icon, "Open", pendingIntent)
                .build();

        Notification notification =  new Notification.Builder(MainActivity.this, channelID)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelID)
                .setNumber(10)
                .setContentIntent(pendingIntent)
                .setActions(action)
                .build();
        notificationManager.notify(notificationID, notification);
    }
}