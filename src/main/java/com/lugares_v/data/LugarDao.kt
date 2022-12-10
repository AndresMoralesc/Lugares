package com.lugares_v.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

import com.lugares_v.model.Lugar

interface LugarDao {


    //valores para la estructura de firestore cloud
    private val coleccion1 = "lugaresApp"

    private val usuario = Firebase.auth.currentUser?.email.toString()

    private val coleccion2 = "misLugares"


    //objeto para la  "conexion" de la base de datos en la nube

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        //Inicializa la conexion con Firestore para poder trabajar
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }


    //CRUD Create Read Update Delete
/// Se recibe un objeto lugar, se valida si el id tiene algo... es una actualizacion, sino se crea
    fun saveLugar(lugar: Lugar) {
        val documento: DocumentReference
        if (lugar.id.isEmpty()) {  //vacio entonces nuevo..

            documento =
                firestore.collection(coleccion1).document(usuario).collection(coleccion2).document()

            lugar.id = documento.id
        } else {// si el id tenia algo... entonces ubico en otro documento}
            documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2)
                .document(lugar.id)


        }

// ahora si se va a registrar la info (nueva o actualiza)

        // registra la actualizacion
        documento.set(lugar).addOnSuccessListener {


                Log.d("saveLugar", "Lugar agregado/actualizado")
            }.addOnCanceledListener {
                Log.d("saveLugar", "Lugar NO agregado/actualizado")
            }

    }

    fun deleteLugar(lugar: Lugar) {
        // se valida si el id tiene algo..

        if (lugar.id.isNotEmpty()) {
            firestore.collection(coleccion1).document(usuario).collection(coleccion2)
                .document(lugar.id).delete().addOnSuccessListener {


                    Log.d("deleteLugar", "Lugar eliminado")
                }.addOnCanceledListener {
                    Log.d("deleteLugar", "Lugar NO eliminado")
                }


        }

    }

    fun getLugares(): MutableLiveData<List<Lugar>> {
        var listaLugares: MutableLiveData<List<Lugar>>()

        firestore

            .collection(coleccion1)
            .document(usuario)
            .collection(coleccion2)
            .addSnapshotListener {
                    instantanea, error ->
                if (error != null) { // se materializo algun error en la generacion de la lista

                    return@addSnapshotListener

                }

                /// si estamos en esta linea.. entonces si se tomo la instantanea
                if (instantanea != null) { // hay datos en la instantanea
                    val lista = ArrayList<Lugar>()

                    // se recorre la instantanea para transformar cada documento en un objeto luga
                    instantanea.documents.forEach {
                        val lugar = it.toObject(Lugar::class.java)

                        if (lugar != null) {

                            lista.add(lugar)


                        }


                    }
                    listaLugares.value= lista

                }

            }



        return listaLugares

    }
}