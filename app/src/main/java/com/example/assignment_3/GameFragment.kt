package com.example.assignment_3

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.assignment_3.databinding.FragmentGameBinding
import kotlinx.coroutines.launch



class GameFragment : Fragment() {

    private lateinit var counter : TextView
    private lateinit var points : TextView
    private lateinit var gameTiles : TextView
    private lateinit var randomButtonList : MutableList<String>
    private lateinit var userInputButtonList : MutableList<String>
    private lateinit var gameLayout : ConstraintLayout
    private lateinit var randomButton : ImageButton
    private  var randomNumbers : Int = 0
    private var gameScore : Int = 0
    private lateinit var view: View
    private lateinit var gameTimer : CountDownTimer
    private lateinit var binding : FragmentGameBinding
    private var tiles : Int = 4



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        randomButtonList = mutableListOf()
        userInputButtonList = mutableListOf()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        view = inflater.inflate(R.layout.fragment_game, container, false)


        counter = view.findViewById(R.id.txtCounter)
        points = view.findViewById(R.id.txtPoints)
        gameTiles = view.findViewById(R.id.txtLevel)
        gameLayout = view.findViewById(R.id.game_constraint_layout)

        points.text = gameScore.toString()
        gameTiles.text = tiles.toString()


        // this is the countdowntimer assigned to the global variable "gameTimer"
        gameTimer = object : CountDownTimer (4000, 1000){

            override fun onTick(seconds: Long) {

                var count = seconds / 1000
                counter.setText("$count")

            }

            override fun onFinish() {
                counter.setText("Start!")
                EnableButtons()
                gameLayout.children.forEach {
                    if (it is ImageButton) {
                        it.setBackgroundColor(Color.BLACK)
                    }
                }

            }
        }

        StartGame()

        // sets an onclick event listener for the image buttons
        gameLayout.children.forEach {
            if (it is ImageButton) {
                it.setOnClickListener{ it ->
                    UserClickButton(it)
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun EnableButtons(){
        gameLayout.children.forEach {
            if (it is ImageButton) {
                it.isEnabled = true
            }
        }
    }

    private fun DisableButtons(){
        gameLayout.children.forEach {
            if (it is ImageButton) {
                it.isEnabled = false
            }
        }
    }

    /*
        This function starts the timer and randomly chooses 4 tiles colored cyan for the player to choose
    */
    private fun StartGame(){


        lifecycleScope.launch {

            gameTimer.start()
            DisableButtons()

            repeat(tiles){
                randomNumbers = (1..36).random()

                if(randomButtonList.contains(randomNumbers.toString())){
                    randomNumbers++
                }

                randomButton = view.findViewWithTag(randomNumbers.toString())
                randomButton.setBackgroundColor(Color.CYAN)
                randomButtonList.add(randomButton.tag.toString())

            }

        }

    }

    /*
    *  This function clears out all lists and turns all tiles to black
    */
    private fun ReloadGame(){

        gameLayout.children.forEach {
            if (it is ImageButton) {
                it.setBackgroundColor(Color.BLACK)
            }
        }

        randomButtonList.clear()
        userInputButtonList.clear()

    }

    /*
    * This function is used in the onClick event listener declared with onCreateView
    */
    private fun UserClickButton(view: View){
        view.setBackgroundColor(Color.CYAN)
        userInputButtonList.add(view.tag.toString())

        WinLoseConditions()


    }

    /*
    * This function checks if the player got the tiles right, increments score by 10 pts.
    */
    private fun WinLoseConditions(){

        if(userInputButtonList.containsAll(randomButtonList)){
            gameScore += 10
            points.text = gameScore.toString()

            ReloadGame()
            StartGame()
        }

        else if(userInputButtonList.count() >= tiles && !userInputButtonList.containsAll(randomButtonList)){

            points.text = gameScore.toString()

            ReloadGame()
            StartGame()
        }


    }



}
