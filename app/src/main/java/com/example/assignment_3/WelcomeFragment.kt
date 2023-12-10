package com.example.assignment_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_3.data.Player
import com.example.assignment_3.data.PlayerViewModel


class WelcomeFragment : Fragment() {

    private lateinit var btnSubmit : Button
    private lateinit var view : View
    private lateinit var playerViewModel : PlayerViewModel
    private var playerName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = inflater.inflate(R.layout.fragment_welcome, container, false)

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)


        btnSubmit = view.findViewById(R.id.btnAddPlayer)

        btnSubmit.setOnClickListener{
            AddPlayertoDatabase()
        }


        // Inflate the layout for this fragment
        return view
    }

    private fun AddPlayertoDatabase() {
        playerName = view.findViewById<TextView>(R.id.txtName).text.toString()

        if(playerName != ""){
            val player = Player(0, playerName, 0)
            playerViewModel.addPlayer(player)

            Toast.makeText(requireContext(), "Added player", Toast.LENGTH_SHORT).show()
        }
    }


}