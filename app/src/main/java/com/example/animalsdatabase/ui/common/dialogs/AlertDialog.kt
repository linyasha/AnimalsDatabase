package com.example.animalsdatabase.ui.common.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class AlertDialog: DialogFragment() {

    interface OnDialogResultListener {
        fun onDialogResult(requestCode: Int, resultCode: Int, args: Bundle?)
    }

    companion object {
        const val RESULT_POSITIVE = 0
        const val RESULT_NEGATIVE = 1
        const val RESULT_DISMISS = 3

        private const val KEY_REQUEST = "request"
        private const val KEY_FRAGMENT = "fragment"
        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"
        private const val KEY_POSITIVE = "positive"
        private const val KEY_NEGATIVE = "negative"
        private const val KEY_CANCELABLE = "cancelable"
        private const val KEY_VIEW = "view"
        private const val KEY_ARGS = "args"

        fun show(
            fragment: Fragment,
            requestCode: Int = 0,
            title: String? = null,
            message: String? = null,
            positiveButton: String? = fragment.getString(android.R.string.ok),
            negativeButton: String? = null,
            neutralButton: String? = null,
            cancelable: Boolean = true,
            @LayoutRes customView: Int? = null,
            args: Bundle? = null) {
            AlertDialog().apply {
                arguments = bundleOf(KEY_REQUEST to requestCode, KEY_FRAGMENT to true, KEY_TITLE to title, KEY_MESSAGE to message, KEY_POSITIVE to positiveButton, KEY_NEGATIVE to negativeButton, KEY_CANCELABLE to cancelable, KEY_VIEW to customView, KEY_ARGS to args)
                show(fragment.childFragmentManager, null)
            }
        }
    }

    private var listener: OnDialogResultListener? = null
    private var args: Bundle? = null
    private var requestCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = if (requireArguments().getBoolean(KEY_FRAGMENT)) {
            requireParentFragment() as? OnDialogResultListener
        } else {
            requireActivity() as? OnDialogResultListener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        with(requireArguments()) {
            requestCode = getInt(KEY_REQUEST)
            args = getBundle(KEY_ARGS)
            builder.setTitle(getString(KEY_TITLE))
            builder.setMessage(getString(KEY_MESSAGE))
            getString(KEY_POSITIVE)?.let {
                builder.setPositiveButton(it) { _, _ ->
                    listener?.onDialogResult(requestCode, RESULT_POSITIVE, args)
                    dismiss()
                }
            }
            getString(KEY_NEGATIVE)?.let {
                builder.setNegativeButton(it) { _, _ ->
                    listener?.onDialogResult(requestCode, RESULT_NEGATIVE, args)
                    dismiss()
                }
            }
            getInt(KEY_VIEW).let { if (it > 0) builder.setView(it) }
            builder.setCancelable(getBoolean(KEY_CANCELABLE))
        }
        return builder.create()
    }



    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        listener?.onDialogResult(requestCode, RESULT_DISMISS, args)
    }
}