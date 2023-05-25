package com.example.torniquete

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class LeerQR  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leerqr)
    }

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
            //else { binding.leecturaQr.text=result.contents.toString() }
        }
        else { super.onActivityResult(requestCode, resultCode, data) }
    }
}