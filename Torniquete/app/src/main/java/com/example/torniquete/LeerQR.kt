package com.example.torniquete

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.torniquete.databinding.ActivityLeerqrBinding
import com.google.zxing.integration.android.IntentIntegrator

class LeerQR  : AppCompatActivity() {

    private lateinit var binding: ActivityLeerqrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_leerqr)
        binding = ActivityLeerqrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.scanQRbtn.setOnClickListener {
            leerCodigo()
            //Toast.makeText(this,"Sirve botón", Toast.LENGTH_SHORT).show()
        }
    }

    //*
    private fun leerCodigo()
    {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Lectura de QRs")
        integrator.setTimeout(15000)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null)
        {
            if (result.contents == null)
            {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            }
            else { binding.resultQR.text=result.contents.toString() }
        }
        else { super.onActivityResult(requestCode, resultCode, data) }
    }//*/
}