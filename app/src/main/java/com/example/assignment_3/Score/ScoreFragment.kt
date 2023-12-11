package com.example.assignment_3.Score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_3.Game.LstTopScores
import com.example.assignment_3.R
import com.example.assignment_3.data.PlayerViewModel
import com.example.assignment_3.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var view : View
    private lateinit var binding : FragmentScoreBinding
    private lateinit var playerViewModel : PlayerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*
    * The Score Fragment retrieves the top 3 players from player_score table in the player database
    * The List of players are bound to the RecyclerView
    */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = inflater.inflate(R.layout.fragment_score, container, false)

        binding = FragmentScoreBinding.inflate(inflater, container, false)


        val adapter = LstTopScores()
        val recyclerView = binding.lstPlayers
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // player view model is initialized and the adapter is set to read data
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.readAllData.observe(viewLifecycleOwner, Observer { player ->
            adapter.SetData(player)
        })


        // Inflate the layout for this fragment
        return binding.root
    }


}