package pe.tumicro.android.util;

import android.content.DialogInterface;
import android.support.annotation.Nullable;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.BasePermissionListener;

import pe.tumicro.android.ui.BaseActivity;

/**
 * Created by josue on 29/01/18 for TuRuta_r4
 */

public class PermissionUtil {
    public static void ensureLocationPermissionAndSettings(BaseActivity activity,
                                                           @Nullable OnGrantedListener listener) {
        ensureLocationPermissionAndSettings(activity, listener, null, null);
    }
    public static void ensureLocationPermissionAndSettings(BaseActivity activity,
                                                           @Nullable OnGrantedListener listener,
                                                           @Nullable OnLocationActivationFailedListener onLocationActivationFailedListener,
                                                           @Nullable OnPermissionDeniedListener onPermissionDeniedListener) {
        Dexter.withActivity(activity)
                .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new BasePermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        activity.checkAndPromptLocationSettings(
                            LocationListenerWithLifecycle.mLocationRequestForCompassUse,
                            (success) -> {
                                if (success) {
                                    if (listener != null) {
                                        listener.onGranted();
                                    }
                                } else {
                                    if (onLocationActivationFailedListener != null) {
                                        onLocationActivationFailedListener.onLocationActivationFailed();
                                    }
                                }
                            });
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (onPermissionDeniedListener != null) {
                            onPermissionDeniedListener.onPermissionDenied();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            PermissionRequest permission, final PermissionToken token) {
                        new android.app.AlertDialog.Builder(activity)
                                .setMessage("Se necesita permiso a tu ubicacion")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        token.continuePermissionRequest();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override public void onError(DexterError error) {
                    }
                })
                .check();
    }

    public static void ensureLocationPermission(BaseActivity activity,
                                                           @Nullable OnGrantedListener listener,
                                                           @Nullable OnPermissionDeniedListener onPermissionDeniedListener) {
        Dexter.withActivity(activity)
                .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new BasePermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (listener != null) listener.onGranted();
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (onPermissionDeniedListener != null) {
                            onPermissionDeniedListener.onPermissionDenied();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            PermissionRequest permission, final PermissionToken token) {
                        new android.app.AlertDialog.Builder(activity)
                                .setMessage("Se necesita permiso a tu ubicacion")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        token.continuePermissionRequest();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override public void onError(DexterError error) {
                    }
                })
                .check();
    }

    public interface OnGrantedListener {
        void onGranted();
    }

    public interface OnLocationActivationFailedListener {
        void onLocationActivationFailed();
    }

    public interface OnPermissionDeniedListener {
        void onPermissionDenied();
    }
}
