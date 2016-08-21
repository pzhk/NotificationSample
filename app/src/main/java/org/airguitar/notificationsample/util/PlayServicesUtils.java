package org.airguitar.notificationsample.util;

import android.app.Activity;
import android.content.DialogInterface;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class PlayServicesUtils {

    /**
     * GooglePlayServicesのバージョンを確認しアップデートが必要であれば、ダイアログを表示する。
     * 「更新」ボタン以外をタップすると、activityを閉じる。
     * @param activity
     * @return
     */
    public static boolean checkGooglePlayServices(final Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        Logger.d("isGooglePlayServicesAvailable:" + resultCode);
        if (resultCode == ConnectionResult.SUCCESS){
            return true;
        }

        apiAvailability.getErrorDialog(activity, resultCode, 0, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                activity.finish();
            }
        }).show();
        return  false;
    }
}
