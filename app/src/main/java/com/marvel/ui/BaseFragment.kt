package com.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.marvel.alert_dialog.MarvelAlertDialog

abstract class BaseFragment : Fragment() {
    private var pDialog: MarvelAlertDialog? = null
    private var isActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pDialog = MarvelAlertDialog(requireContext())
        pDialog!!.setCancelable(false)
    }

    abstract fun setupViews()

    open fun apiPopUP(errorApi:String){
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Api error")
        alertDialogBuilder.setMessage("Error in the following Api's: \n$errorApi")
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss()
            requireActivity().finish()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    open fun showLoader() {
        if (pDialog == null) {
            pDialog = MarvelAlertDialog(requireContext())
        }
        pDialog!!.show()
    }

    open fun hideLoader() {
        val b = true
        if (b && pDialog != null) {
            pDialog!!.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        isActive = true
    }

    override fun onStop() {
        super.onStop()
        isActive = false
    }

    open fun isUIAlive(): Boolean {
        return isActive
    }
}