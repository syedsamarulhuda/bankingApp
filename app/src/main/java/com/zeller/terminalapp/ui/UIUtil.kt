package com.zeller.terminalapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.zeller.terminalapp.R
import com.zeller.core_common.util.Constants.CTA_CANCEL

fun showTransactionAlertDialog(
    activity: Activity,
    title: String,
    positiveCtaName: String,
    onPositiveButtonClicked: (String?) -> Unit
) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    val customLayout: View =
        activity.layoutInflater.inflate(R.layout.transaction_alert_layout, null)
    builder.apply {
        setView(customLayout)
        customLayout.findViewById<TextView>(R.id.alertTile).text = title
        setPositiveButton(positiveCtaName) { _, _ ->
            val editText = customLayout.findViewById<EditText>(R.id.editText)
            onPositiveButtonClicked.invoke(editText.text.toString())
        }
        setNegativeButton(CTA_CANCEL) { dialog, _ ->
            dialog.dismiss()
        }
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}