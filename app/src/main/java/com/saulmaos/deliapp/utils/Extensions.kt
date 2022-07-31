package com.saulmaos.deliapp.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.saulmaos.deliapp.R

fun ImageView.useGlide(url: String) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_web)
        .into(this)
}