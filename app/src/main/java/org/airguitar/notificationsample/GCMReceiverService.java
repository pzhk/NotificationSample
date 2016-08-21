package org.airguitar.notificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import org.airguitar.notificationsample.util.Logger;

public class GCMReceiverService extends GcmListenerService {
    private static final String TAG = GCMReceiverService.class.getSimpleName();
    int countNotification = 0;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String msg = data.getString("msg");
        Logger.d("onMessageReceived: from=" + from + "  message=" + msg);

        notifyUser(getApplicationContext(), msg);
    }

    @Override
    public void onDeletedMessages() {
        Logger.d("onDeletedMessages:");
    }

    @Override
    public void onMessageSent(String msgId) {
        Logger.d("onMessageSent:" + msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {
        Logger.d("onSendError:" + msgId + "," + error);
    }

    public void notifyUser(Context context, String data) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("data", data);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentTitle("New Notification");
        builder.setContentIntent(pendingIntent);
        builder.setContentText(data);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        builder.setVibrate(new long[]{1000, 1000});
        notificationManager.notify(countNotification++, builder.build());

        Log.v(TAG, "count " + countNotification);
    }
}