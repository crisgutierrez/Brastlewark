package com.example.brastlewark.ext

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.brastlewark.R

fun ImageView.loadRemoteAsset(
        avatarUrl: Uri,
        circular: Boolean = false,
        placeholderId: Int = R.drawable.ic_placeholder,
        onError: () -> Unit = {}
    ) {
        loadImage(
            avatarUrl,
            circular,
            placeholderId = placeholderId,
            onError = onError
        )
    }

    fun ImageView.loadImage(
        uri: Uri,
        circular: Boolean = false,
        @DrawableRes placeholderId: Int = 0,
        clearCache: Boolean = false,
        onLoaded: (resource: Drawable?) -> Unit = {},
        onError: () -> Unit = {}
    ) {

        var requestOptions = RequestOptions().override(this.height)

        if (circular) {
            requestOptions = requestOptions.circleCrop()
        }

        if (placeholderId != 0) {
            requestOptions = requestOptions.placeholder(placeholderId)
            requestOptions = requestOptions.error(placeholderId)
        }

        if (clearCache) {
            Glide.get(context).clearMemory()
            requestOptions = requestOptions
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        }

        Glide.with(context)
            .applyDefaultRequestOptions(requestOptions)
            .load(uri)
            .into(object: CustomViewTarget<ImageView, Drawable>(this) {

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    view.setImageDrawable(errorDrawable)
                    onError()
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    view.setImageDrawable(placeholder)
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    view.setImageDrawable(resource)
                    onLoaded(resource)
                }
            })
    }
