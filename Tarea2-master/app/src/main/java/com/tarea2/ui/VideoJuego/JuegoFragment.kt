package com.tarea2.ui.VideoJuego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tarea2.R
import com.tarea2.databinding.FragmentJuegoBinding
import com.tarea2.viewmodel.JuegoViewModel

class JuegoFragment : Fragment() {

    private var _binding: FragmentJuegoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var juegoViewModel : JuegoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         juegoViewModel =
            ViewModelProvider(this).get(JuegoViewModel::class.java)

        _binding = FragmentJuegoBinding.inflate(inflater, container, false)

        binding.addJuego.setOnClickListener{
            findNavController().navigate(R.id.action_nav_juego_to_addJuegoFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}