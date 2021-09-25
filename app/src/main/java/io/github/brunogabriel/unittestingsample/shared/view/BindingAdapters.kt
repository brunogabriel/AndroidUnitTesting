package io.github.brunogabriel.unittestingsample.shared.view

import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter

@BindingAdapter("displayChild")
fun displayChild(viewFlipper: ViewFlipper, childIndex: Int) {
    viewFlipper.displayedChild = childIndex
}