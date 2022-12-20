package com.tarea2.ui.VideoJuego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tarea2.R
import com.tarea2.databinding.FragmentAddJuegoBinding
import com.tarea2.model.Juego
import com.tarea2.viewmodel.JuegoViewModel

class addJuegoFragment : Fragment() {

    private var _binding: FragmentAddJuegoBinding? = null
    private val binding get() = _binding!!

    private lateinit var juegoViewModel : JuegoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        juegoViewModel =
            ViewModelProvider(this)[JuegoViewModel::class.java]

        _binding = FragmentAddJuegoBinding.inflate(inflater, container, false)

        binding.btnAgregar.setOnClickListener{
            addJuego()
        }

        return binding.root
    }

    private fun addJuego() {
        val marca = binding.etMarca.text.toString()
        val modelo = binding.etModelo.text.toString()
        val placa = binding.etPlaca.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val apellido1 = binding.etApellido1.text.toString()
        val apellido2 = binding.etApellido2.text.toString()
        val nombre = binding.etNombre.text.toString()
        val web = binding.etWeb.text.toString()
    if(nombre.isNotEmpty()){
        val juego= Juego(0,marca,modelo,placa,telefono,apellido1,apellido2,nombre,web )
        juegoViewModel.addJuego(juego)
        Toast.makeText(requireContext(),getString(R.string.msg_juego_added),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addJuegoFragment_to_nav_juego)
    }else{
        Toast.makeText(requireContext(),getString(R.string.msg_error),Toast.LENGTH_SHORT).show()


    }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}