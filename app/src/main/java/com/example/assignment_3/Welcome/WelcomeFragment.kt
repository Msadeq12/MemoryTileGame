package com.example.assignment_3.Welcome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_3.R
import com.example.assignment_3.data.Player
import com.example.assignment_3.data.PlayerViewModel


class WelcomeFragment : Fragment() {

    private lateinit var btnSubmit : Button
    private lateinit var view : View
    private lateinit var playerViewModel : PlayerViewModel
    private var playerName : String = ""
    private lateinit var preferenceName: SharedPreferences

    // clears out the shared preferences when welcome fragment is opened
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferenceName = requireContext().getSharedPreferences("player_name", Context.MODE_PRIVATE)
        preferenceName.edit().clear().apply()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = inflater.inflate(R.layout.fragment_welcome, container, false)

        // initialized player view model to add to database
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)





        btnSubmit = view.findViewById(R.id.btnAddPlayer)

        // sets the onclick listener to the function below
        btnSubmit.setOnClickListener{
            preferenceName = requireContext().getSharedPreferences("player_name", Context.MODE_PRIVATE)
            preferenceName.edit().clear().apply()

            AddPlayertoDatabase()
        }


        // Inflate the layout for this fragment
        return view
    }

    // adds the player name to the database with the game score automatically set to zero.
    private fun AddPlayertoDatabase() {
        playerName = view.findViewById<TextView>(R.id.txtName).text.toString()

        if(playerName != ""){

            preferenceName = requireContext().getSharedPreferences("player_name", Context.MODE_PRIVATE)
            preferenceName.edit().putString("name", playerName).apply()

            val player = Player(0, playerName, 0)
            playerViewModel.addPlayer(player)

            Toast.makeText(requireContext(), "Added player", Toast.LENGTH_SHORT).show()
        }
    }




}