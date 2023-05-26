package com.example.torniquete

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.torniquete.databinding.ActivityLeerqrBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import kotlin.concurrent.schedule
import java.util.Timer

class LeerQR  : AppCompatActivity() {

    private lateinit var binding: ActivityLeerqrBinding
    private lateinit var auth: FirebaseAuth;

    val database = Firebase.database
    val myRef = database.getReference("claves")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth;

        binding = ActivityLeerqrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.scanQRbtn.setOnClickListener {
            leerCodigo()
            //Toast.makeText(this,"Sirve botón", Toast.LENGTH_SHORT).show()
        }
        binding.logoutBtn.setOnClickListener {
            logOut()
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
                Toast.makeText(this, "No se encontró código QR", Toast.LENGTH_LONG).show()
            }
            else {
                //Inserte el leer el nodo
                myRef.child(result.contents.toString()).child("status").get().addOnSuccessListener {
                    //Log.i("firebase", "Got value ${it.value}")
                    var res = it.value.toString()
                    Toast.makeText(this, res, Toast.LENGTH_LONG).show()
                    if (res == "Generado") {
                        myRef.child(result.contents.toString()).child("status").setValue("Utilizado")
                        binding.resultQR.text = "¡Que tenga un buen viaje!"
                        Timer().schedule(6000) {
                            binding.resultQR.text = ""
                        }
                    } else {
                        binding.resultQR.text = "QR utilizado. Genere un nuevo QR para ingresar"
                        Timer().schedule(8000) {
                            binding.resultQR.text = ""
                        }
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Hubo un error en la lectura del QR", Toast.LENGTH_LONG).show()
                }
                //var res = myRef.child(result.contents.toString()).child("status")
                //Asignación de valor "utilizado" al nodo
                //myRef.child(result.contents.toString()).child("status").setValue("Utilizado")
                //Toast.makeText(this, res, Toast.LENGTH_LONG).show()
            }
        }
        else { super.onActivityResult(requestCode, resultCode, data) }
    }//*/
    fun logOut() {
        auth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}