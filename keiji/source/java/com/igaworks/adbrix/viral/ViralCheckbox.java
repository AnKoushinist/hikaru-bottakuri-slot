package com.igaworks.adbrix.viral;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;

public class ViralCheckbox extends ImageView {
    private boolean checked;
    private Context context;
    private ViralCheckboxClickListener listener;

    public abstract class ViralCheckboxClickListener implements OnClickListener {
        public void onClick(ViralCheckbox viralCheckbox) {
            ViralCheckbox.this.checked = !ViralCheckbox.this.checked;
            if (ViralCheckbox.this.checked) {
                if (CommonHelper.CheckPermissionForCommonSDK(ViralCheckbox.this.context)) {
                    CPECompletionHandler.getImageDownloader(ViralCheckbox.this.context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_pressed.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_pressed.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                        public void onResultCustom(Bitmap bitmap) {
                            ViralCheckbox.this.setImageBitmap(bitmap);
                        }
                    });
                } else {
                    InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                        public void run() {
                            CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_pressed.png");
                            new Handler(ViralCheckbox.this.context.getMainLooper()).post(new Runnable() {
                                public void run() {
                                }
                            });
                        }
                    });
                }
            } else if (CommonHelper.CheckPermissionForCommonSDK(ViralCheckbox.this.context)) {
                CPECompletionHandler.getImageDownloader(ViralCheckbox.this.context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        ViralCheckbox.this.setImageBitmap(bitmap);
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png");
                        new Handler(ViralCheckbox.this.context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    ViralCheckbox.this.setImageBitmap(bitmapFromURL);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public ViralCheckbox(Context context) {
        super(context);
        this.context = context;
    }

    public void setClickListener(ViralCheckboxClickListener viralCheckboxClickListener) {
        if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
            CPECompletionHandler.getImageDownloader(this.context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    ViralCheckbox.this.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_check_normal.png");
                    new Handler(ViralCheckbox.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                ViralCheckbox.this.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.listener = viralCheckboxClickListener;
        setOnClickListener(viralCheckboxClickListener);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.listener.onClick(this);
    }
}
