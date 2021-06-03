package ro.alexmamo.firebase.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ro.alexmamo.firebase.utils.Constants.MOVIE_POSTER
import ro.alexmamo.firebase.utils.Constants.PRODUCT_LOGO

class BindingAdapters {
    companion object {
        @BindingAdapter(MOVIE_POSTER)
        @JvmStatic
        fun setMoviePosterToImageView(imageView: ImageView, posterUrl: String) {
            Glide.with(imageView.context)
                .load(posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }

        @BindingAdapter(PRODUCT_LOGO)
        @JvmStatic
        fun setProductLogoToImageView(imageView: ImageView, drawable: Int) {
            Glide.with(imageView.context)
                .load(drawable)
                .into(imageView)
        }
    }
}