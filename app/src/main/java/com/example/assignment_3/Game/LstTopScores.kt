package com.example.assignment_3.Game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_3.data.Player
import com.example.assignment_3.databinding.TopScoresBinding


// This is a custom class inheriting from RecyclerView used in the Score Fragment
class LstTopScores() : RecyclerView.Adapter<LstTopScores.MyViewHolder>() {

    private var playerList = emptyList<Player>()
    private lateinit var binding : TopScoresBinding
    private lateinit var view : View

    class MyViewHolder(binding : TopScoresBinding) : RecyclerView.ViewHolder(binding.root){

    }

    // this override function uses the custom layout "top_scores.xml" to be used in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = TopScoresBinding.inflate(inflater, parent, false)

        //return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.top_scores, parent, false))

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    // this function binds the data from the table rows to the Score Fragment Page with the help of view binding
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]

        binding.lstId.text = currentItem.Id.toString()
        binding.lstName.text = currentItem.Name
        binding.lstScore.text = currentItem.Score.toString()

    }

    // used to set the data from the player_score table to the adapter assigned to the RecycleView
    fun SetData(player : List<Player>){
        this.playerList = player
        notifyDataSetChanged()
    }




}