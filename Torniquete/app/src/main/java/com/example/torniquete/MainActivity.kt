package com.example.torniquete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth;

        val login = findViewById<Button>(R.id.login)
        val usuario = findViewById<EditText>(R.id.emailTextField)
        val contra = findViewById<EditText>(R.id.passwordTextField)
        login.setOnClickListener{
            auth.signInWithEmailAndPassword(usuario.text.toString(),contra.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,LeerQR::class.java))
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        validarUsuario(currentUser)
    }

    public fun validarUsuario(usuario: FirebaseUser?) {
        if(usuario==null)
        {
            Toast.makeText(this,"No hay usuarios autenticados", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this,"Favor de autenticarse", Toast.LENGTH_LONG).show()
        }
    }
}