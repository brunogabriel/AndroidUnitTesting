package io.github.brunogabriel.unittestingsample.shared.databinding

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> Activity.bind(
    @LayoutRes layoutRes: Int,
    block: T.() -> Unit
): T {
    return DataBindingUtil.setContentView<T>(this, layoutRes).apply {
        block(this)
    }
}