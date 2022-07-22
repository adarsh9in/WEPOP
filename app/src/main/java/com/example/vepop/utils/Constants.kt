package com.example.vepop.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Constants {

    const val USERS: String = "users"
    const val VEPOP_PREFERENCES: String = "VepopPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_userna me"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1

    fun showImageChooser(activity: Activity) {
// An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
// Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)


    }
}