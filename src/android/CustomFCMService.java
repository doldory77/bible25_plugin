package com.sangs.plugin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sangs.bible25.MainActivity;
import com.sangs.bible25.R;

import java.util.Map;

public class CustomFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("메시지", "From: " + remoteMessage.getFrom());
        Log.d("메시지", "message notification data: " + remoteMessage.getData().toString());
        Log.d("메시지", "message notification title: " + remoteMessage.getData().get("title"));
        Log.d("메시지", "message notification body: " + remoteMessage.getData().get("body"));
        Log.d("메시지", "message notification link: " + remoteMessage.getData().get("link"));

        Map<String, String> pushDataMap = remoteMessage.getData();
        sendNotification(pushDataMap);
    }

    private void sendNotification(Map<String, String> dataMap) {

        /* 링크에 값이 있으면 팝업으로 호출 */
        if (dataMap.get("link") != null && dataMap.get("link").length() > 0) {
            try {
                AssetFileDescriptor afd = getAssets().openFd("www/assets/audio/click_on.mp3");
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(this, CustomPopup.class);
            intent.putExtra("linkUrl", dataMap.get("link"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        /* 기본푸시 */
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel_id");
        builder.setSmallIcon(R.mipmap.icon)
                .setContentTitle(dataMap.get("title"))
                .setContentText(dataMap.get("body"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.WHITE, 1500, 1500)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

}
