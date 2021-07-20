package com.secbreel.notes.ui.ext

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


private val READ_STORAGE_REQUEST_CODE = 500
private val WRITE_STORAGE_REQUEST_CODE = 501


fun getPermissions(context: Activity) {
    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    )
        ActivityCompat.requestPermissions(
            context,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_STORAGE_REQUEST_CODE
        )
    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    )
        ActivityCompat.requestPermissions(
            context,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            WRITE_STORAGE_REQUEST_CODE
        )
}