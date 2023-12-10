package com.example.assignment_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assignment_3.data.Player
import com.example.assignment_3.databinding.TopScoresBinding


class LstTopScores() : RecyclerView.Adapter<LstTopScores.MyViewHolder>() {

    private var playerList = emptyList<Player>()
    private lateinit var binding : TopScoresBinding
    private lateinit var view : View
    class MyViewHolder(binding : TopScoresBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = TopScoresBinding.inflate(inflater, parent, false)

        //return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.top_scores, parent, false))

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]

        binding.lstName.text = currentItem.Name
        binding.lstScore.text = currentItem.Score.toString()

    }

    fun SetData(player : List<Player>){
        this.playerList = player
        notifyDataSetChanged()
    }




}