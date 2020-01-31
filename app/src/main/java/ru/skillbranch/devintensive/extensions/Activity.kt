package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard(){
    val imm:InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val textMessage: EditText = et_message
    imm.hideSoftInputFromWindow(textMessage.windowToken, 0)
}