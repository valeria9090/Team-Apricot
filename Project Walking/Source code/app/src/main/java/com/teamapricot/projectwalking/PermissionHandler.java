package com.teamapricot.projectwalking;

import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Erik Wahlberger
 * @version 2021-09-17
 *
 * PermissionHandler is used to check for already granted permissions, as well as
 * request new permissions in an easier way than by using the standard implementation by Google.
 * Make sure to add each permission to the AndroidManifest.xml using {@literal <uses-permission android:name="..." />}.
 */
public class PermissionHandler {
    /**
     * Checks if multiple permissions have already been granted.
     * @param permissions The permissions to be checked. Can be found within PackageManager.permissions
     * @return A {@code Map<String, Boolean> }. The keys ({@code String}) represents each permission. The values ({@code Boolean}) represent the current permission status for specific permissions.
     * To check if a permission has already been granted or not, use the {@code Map} return value with the searched permission as the key.
     */
    public Map<String, Boolean> checkPermissions(String[] permissions) {
        Map<String, Boolean> permissionResults = new HashMap<>();

        for (String permission : permissions) {
            if (permissionResults.keySet().contains(permission)) {
                continue;
            }

            Boolean isGranted = ContextCompat.checkSelfPermission(this.activity, permission) == PackageManager.PERMISSION_GRANTED;
            permissionResults.put(permission, isGranted);
        }

        return permissionResults;
    }

    /**
     * Checks if a permission has already been granted.
     * @param permission The permission to be checked. Can be found within PackageManager.permissions
     * @return A {@code boolean} value representing whether or not the permission has already been granted.
     */
    public boolean checkPermission(String permission) {
        Map<String, Boolean> permissionResults = checkPermissions(new String[]{permission});

        if (permissionResults.keySet().contains(permission)) {
            return permissionResults.get(permission);
        }

        return false;
    }
}
