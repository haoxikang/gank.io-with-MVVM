package com.fall.gank.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class Utils {
    public static boolean copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("copy", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
      return true;
    }
}
