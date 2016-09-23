package com.riverview.hackthon.mixandmatch.Utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Created by wiraj on 3/13/15.
 */
public class AppUtil {


    private static final String TAG = AppUtil.class.getSimpleName();

    public static final String TEMP_PHOTO_FILE = "tmp.JPEG";

    public static void toggle(final FrameLayout v, float height, int animationInterval) {

        height = (float) (height * 1);

        v.setVisibility(View.VISIBLE);
        ValueAnimator va = ValueAnimator.ofFloat(0f, height).setDuration(
                animationInterval);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = Math.round((float) animation
                        .getAnimatedValue());
                v.getLayoutParams().height = value.intValue();
                v.invalidate();
                v.requestLayout();

            }
        });
        va.setInterpolator(new AccelerateInterpolator(8));
        va.start();
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int height;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            height = display.getHeight(); // deprecated
        } else {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }

        return height;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            width = display.getWidth(); // deprecated

        } else {
            Point size = new Point();
            display.getSize(size);
            width = size.x;

        }

        return width;
    }




    private static Intent pickFromCamera(Activity activity) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,
                "IMG_CAM_SCCRMS.jpg");

        Intent capturePhoto = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        // capturePhoto.setType("image/*");
        capturePhoto.putExtra("crop", "true");
        capturePhoto.putExtra("scale", true);
        capturePhoto.putExtra("aspectX", 1); // Set this to define the X aspect
        // ratio of the shape
        capturePhoto.putExtra("aspectY", 1); // Set this to define the Y aspect
        // ratio of the shape

        Uri u = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Log.d(TAG, "gg " + u.getPath());
        capturePhoto.putExtra(MediaStore.EXTRA_OUTPUT, u);
        capturePhoto.putExtra("outputFormat",
                Bitmap.CompressFormat.PNG.toString());
        return capturePhoto;
    }

    public static Intent pickFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.setType("image/*");
        pickPhoto.putExtra("crop", "true");
        pickPhoto.putExtra("aspectX", 1); // Set this to define the X aspect
        // ratio of the shape
        pickPhoto.putExtra("aspectY", 1); // Set this to define the Y aspect
        // ratio of the shape
        pickPhoto.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
        pickPhoto
                .putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());

//        pickPhoto
//                .putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return pickPhoto;
    }

    public static Uri getTempUri() {
        return Uri.fromFile(getTempFile());
    }

    private static File getTempFile() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            File file = new File(Environment.getExternalStorageDirectory(),
                    TEMP_PHOTO_FILE);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return file;
        } else {
            return null;
        }
    }





    /**
     * This method convets dp unit to equivalent device specific value in
     * pixels.
     *
     * @param dp      A value in dp(Device independent pixels) unit. Which we need
     *                to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent Pixels equivalent to dp according to
     * device
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to device independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent db equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;

    }

    public static AlertDialog showAlert(Context context, String title,
                                        String message, DialogInterface.OnClickListener listener) {
        AlertDialog d;
        try {
            if (listener == null) {
                listener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                };
            }

            d = new AlertDialog.Builder(context).setMessage(message)
                    .setTitle(title).setCancelable(true)
                    .setNeutralButton(android.R.string.ok, listener).show();
            return d;

        } catch (Exception e) {
        }
        return null;
    }

    public static AlertDialog showRecordingAlert(Context context, String title,
                                                 String message, DialogInterface.OnClickListener listener) {
        AlertDialog d;
        try {
            if (listener == null) {
                listener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                };
            }

            d = new AlertDialog.Builder(context).setMessage(message)
                    .setTitle(title).setCancelable(false)
                    .setNeutralButton("Stop Recording", listener).show();
            return d;

        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Show confirm dialog popup
     *
     * @param context
     * @param title
     * @param message
     * @param l
     */
    public static void confirm(Context context, String title, String message,
                               final ConfirmListener l, final CancelListener c) {
        AlertDialog.Builder b = buildAlert(context, title, message);
        if (c != null)
            b.setNegativeButton(
                    context.getResources().getString(android.R.string.cancel),
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            c.onNo();
                        }
                    });
        else
            b.setNegativeButton(
                    context.getResources().getString(android.R.string.cancel), null);

        b.setPositiveButton(
                context.getResources().getString(android.R.string.yes),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        l.onYes();
                    }
                });

        b.show();
    }

    /**
     * Show confirm dialog popup
     *
     * @param context
     * @param title
     * @param message
     * @param l
     */
    public static void confirm(Context context, String title, String message,
                               final ConfirmListener l, final CancelListener c, String yesText, String noText) {
        AlertDialog.Builder b = buildAlert(context, title, message);
        if (c != null)
            b.setNegativeButton(
                    noText,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            c.onNo();
                        }
                    });
        else
            b.setNegativeButton(
                    noText, null);

        b.setPositiveButton(
                yesText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        l.onYes();
                    }
                });

        b.show();
    }


    public static AlertDialog.Builder buildAlert(Context context, String title,
                                                 String message) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context).setMessage(message)
                .setTitle(title).setCancelable(true);
        return dlg;
    }

    public static interface ConfirmListener {

        public void onYes();
    }

    public static interface CancelListener {

        public void onNo();
    }

    /**
     * Check the network connection of the device.
     *
     * @param context current context value
     * @return true if network connection available false otherwise
     */
    public static boolean checkNetworkConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworks = connectivityManager.getActiveNetworkInfo();
        if (activeNetworks != null && activeNetworks.isConnected()) {
            return activeNetworks.isConnectedOrConnecting();
        }
        return false;
    }

    public static void showSystemSettingsDialog(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

        } else {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    public static void showSystemLocationSettingsDialog(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

        } else {
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    public static String getDeviceId(Context mContext) {
        String deviceId = null;

        try {
            TelephonyManager manager = (TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return manager.getDeviceId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceId;
    }

    public static String getDateString(Date date) {
        if (date != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            return formatter.format(date);
        } else {
            return null;
        }
    }

    public static String convertDate(Context mContext, String strDate,
                                     boolean sysFormat) {

        try {
            if (strDate.equals("0000-00-00")) {
                return strDate;
            }

            Date date;
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(strDate);

            Format dateFormat = android.text.format.DateFormat
                    .getDateFormat(mContext);
            String pattern = ((SimpleDateFormat) dateFormat)
                    .toLocalizedPattern();

            DateFormat formatter1;
            if (sysFormat) {
                formatter1 = new SimpleDateFormat(pattern);
            } else {
                formatter1 = new SimpleDateFormat("d MMM yyyy");
            }
            return formatter1.format(date);

        } catch (ParseException e) {
        } catch (Exception e) {
        }

        return strDate;
    }

    public static String convertDateNew(Context mContext, String strDate,
                                        boolean sysFormat) {

        try {
            if (strDate.equals("0000-00-00")) {
                return strDate;
            }

            Date date;
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(strDate);

            Format dateFormat = android.text.format.DateFormat
                    .getDateFormat(mContext);
            String pattern = ((SimpleDateFormat) dateFormat)
                    .toLocalizedPattern();

            DateFormat formatter1;
            if (sysFormat) {
                formatter1 = new SimpleDateFormat(pattern);
            } else {
                formatter1 = new SimpleDateFormat("MMM d, yyyy");
            }
            return formatter1.format(date);

        } catch (ParseException e) {
        } catch (Exception e) {
        }

        return strDate;
    }

    public static String convertDateToServerFormat(Context mContext, String strDate,
                                                   boolean sysFormat) {

        try {
            if (strDate.equals("0000-00-00")) {
                return strDate;
            }

            Date date;
            DateFormat formatter = new SimpleDateFormat("d MMM yyyy");
            date = formatter.parse(strDate);

            Format dateFormat = android.text.format.DateFormat
                    .getDateFormat(mContext);
            String pattern = ((SimpleDateFormat) dateFormat)
                    .toLocalizedPattern();

            DateFormat formatter1;
            if (sysFormat) {
                formatter1 = new SimpleDateFormat(pattern);
            } else {
                formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            }
            return formatter1.format(date);

        } catch (ParseException e) {
        } catch (Exception e) {
        }

        return strDate;
    }

    public static String getNationalityJSON(Context context) {

        try {
            InputStream stream = context.getAssets().open("nationalities.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);

            stream.close();

            return new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get android application version
     *
     * @param context current context
     * @return application version id
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            // throw new RuntimeException("Could not get package name: " + e);
            e.printStackTrace();
        }
        return 0;
    }




    public static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideDefaultKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }









}
