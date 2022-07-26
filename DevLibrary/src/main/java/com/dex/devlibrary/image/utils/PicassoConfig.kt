package com.dex.devlibrary.image.utils

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class PicassoConfig {
    companion object {
        fun getPicasso(context: Context): Picasso {
            return Picasso.Builder(context)
                .indicatorsEnabled(true)
                .loggingEnabled(true)
                .build()
        }
    }
}