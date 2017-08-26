package org.cocos2dx.lib;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public class Cocos2dxHandler extends Handler {
    public static final int HANDLER_SHOW_DIALOG = 1;
    private WeakReference<Cocos2dxActivity> mActivity;

    public static class DialogMessage {
        public String message;
        public String titile;

        public DialogMessage(String str, String str2) {
            this.titile = str;
            this.message = str2;
        }
    }

    public Cocos2dxHandler(Cocos2dxActivity cocos2dxActivity) {
        this.mActivity = new WeakReference(cocos2dxActivity);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case HANDLER_SHOW_DIALOG /*1*/:
                showDialog(message);
                return;
            default:
                return;
        }
    }

    private void showDialog(Message message) {
        DialogMessage dialogMessage = (DialogMessage) message.obj;
        new Builder((Cocos2dxActivity) this.mActivity.get()).setTitle(dialogMessage.titile).setMessage(dialogMessage.message).setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create().show();
    }
}
