package keren.movie.moviekeren.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author hendrawd on 11/17/16
 */

public class Util {

    /**
     * Workaround to get color from various versions of Android
     *
     * @param context context
     * @param id      color resource id
     * @return int color
     */
    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /**
     * Start activity with view shared element transition if the version greater than lollipop
     * or no shared element transition if the version below lollipop
     * Don't forget to add
     * android:transitionName="yourPreferredTransitionName"
     * to the shared elements
     *
     * @param context activity
     * @param intent  intent to start
     * @param view    shared element view
     */
    public static void startActivityWithSharedElementTransitionIfPossible(Context context, Intent intent, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent);
        } else {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    (Activity) context, view, view.getTransitionName()).toBundle());
        }
    }

    public static void openUrl(Context ctx, String url) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ctx.startActivity(browserIntent);
        } catch (Exception e) {
            CustomToast.show(ctx, "Can't open url!");
        }
    }

    /**
     * Convert dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float dp2px(Context context, float dp) {
        //return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * A helper to hide keyboard because that android design patter itself is bad for hiding keyboard
     *
     * @param context context where the keyboard exist
     * @param view    view that currently has focus
     */
    public static void hideKeyboardFrom(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
