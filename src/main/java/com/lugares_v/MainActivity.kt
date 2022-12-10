package com.lugares_v

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import  com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lugares_v.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Definimos un objeto para acceder a la autenticación de Firebase

    private  lateinit var  auth: FirebaseAuth


    //Definimos un objeto para acceder a los elementos de la pantalla xml

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar la autenticación
            FirebaseApp.initializeApp(this)

        auth = Firebase.auth


        //Definir evento onClic del boton Register

        binding.btRegister.setOnClickListener { haceRegistro() }

        binding.btLogin.setOnClickListener { haceLogin() }





    }

    private fun haceLogin() {
        //Recupero la información que el usuario haya escrito
        val email= binding.etCorreo.text.toString()
        val clave= binding.etClave.text.toString()

        //Utilizo el objeto auth para hacer el registro..
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){//si se logró se creó el usuario

                    val user= auth.currentUser
                    refresca(user)
                }else{  //si no se logró hubo un error..
                    Toast.makeText(baseContext,"Falló",Toast.LENGTH_LONG).show()
                    refresca(null)
                }

            }

    }

    private fun haceRegistro() {
        //Recupero la información que el usuario haya escrito
        val email= binding.etCorreo.text.toString()
        val clave= binding.etClave.text.toString()

        Log.d("Autenticandose","Haciendo llamado de autenticación")
        //Utilizo el objeto auth para hacer el registro..
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
               if(task.isSuccessful){//si se logró se creó el usuario
                   Log.d("Autenticando","se autentico")
                    val user= auth.currentUser
                   refresca(user)
               }else{  //si no se logró hubo un error..
                   Log.d("Autenticando","Error de Autenticación")
                   Toast.makeText(baseContext,"Falló",Toast.LENGTH_LONG).show()
                   refresca(null)                   
               }

            }
        Log.d("Autenticando","Sale del proceso..")
    }

    private fun refresca(user: FirebaseUser?) {
 if(user !=null) { //Si hay un usuario entonces paso a la pantalla principal
    val intent = Intent(this,Principal::class.java)

     startActivity(intent)
 }
    }
// Esto se ejecuta toda vez que se presente el app en la pantalla, valida si hay un usuario autenticando

    public override fun onStart(){
        super.onStart()
        val usuario= auth.currentUser
        refresca(usuario)

    }


}