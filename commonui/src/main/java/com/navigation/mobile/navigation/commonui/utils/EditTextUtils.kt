package com.navigation.mobile.navigation.commonui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Function to simplify passing function to run [afterTextChanged]
 */
@Suppress("unused")
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
