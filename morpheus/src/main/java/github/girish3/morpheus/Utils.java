package github.girish3.morpheus;

import android.content.Context;
import android.support.annotation.DimenRes;

/**
 * Created by Girish on 07/01/16.
 */
public class Utils {

    public static int dpTopx(Context context, @DimenRes int dp) {
        return context.getResources().getDimensionPixelSize(dp);
    }
}
