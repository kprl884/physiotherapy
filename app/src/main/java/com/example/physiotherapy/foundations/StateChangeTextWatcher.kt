package com.example.physiotherapy.foundations

import android.text.Editable
import android.text.TextWatcher

open class StateChangeTextWatcher : TextWatcher{

    var previousValue : String? = null

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        previousValue = p0.toString()
    }
}