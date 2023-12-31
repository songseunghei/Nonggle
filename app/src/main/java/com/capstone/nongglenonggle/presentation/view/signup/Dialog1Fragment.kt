package com.capstone.nongglenonggle.presentation.view.signup

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.capstone.nongglenonggle.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Dialog1Fragment : DialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_dialog1,null)
    val closebtn = dialogView.findViewById<ImageView>(R.id.closebtn)
    closebtn.setOnClickListener{
        dismiss()
    }
        val resourceId = resources.getIdentifier("clause","raw","com.capstone.nongglenonggle")
        if(resourceId != 0){
            val inputStream = resources.openRawResource(resourceId)
            val reader=BufferedReader(InputStreamReader(inputStream))
            val stringBuilder=StringBuilder()
            var line:String?

            try{
                while(reader.readLine().also{line=it} != null){
                    stringBuilder.append(line).append('\n')
                }
            } catch(e: IOException){
                Log.e("error","error while doing")
            }finally {
                try {
                    inputStream.close()
                }catch (e:IOException){
                    Log.e("error","error while Stream close")
                }
            }
            val longString = stringBuilder.toString()
            val text = dialogView.findViewById<TextView>(R.id.longtext)
            text.setText(longString)
        }


        return AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()


    }

}