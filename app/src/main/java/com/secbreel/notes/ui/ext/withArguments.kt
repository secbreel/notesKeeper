package com.secbreel.notes.ui.ext

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun Fragment.withArguments(configure: (Bundle) -> Unit) =
    apply { arguments = Bundle().apply(configure) }

inline fun Intent.withArguments(configure: (Bundle) -> Unit) =
    apply { putExtras(Bundle().apply(configure)) }