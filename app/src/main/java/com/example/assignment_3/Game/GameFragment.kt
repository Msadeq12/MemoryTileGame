package com.example.assignment_3.Game

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_3.R
import com.example.assignment_3.data.PlayerViewModel
import kotlinx.coroutines.delay
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
    private var tiles : Int = 4
    private lateinit var markImage : ImageView
    private lateinit var correct : Drawable
    private lateinit var wrong : Drawable

    private lateinit var playerViewModel : PlayerViewModel
    private lateinit var preferenceName : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        randomButtonList = mutableListOf()
        userInputButtonList = mutableListOf()
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

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
        markImage = view.findViewById(R.id.imgMark)
        correct = ResourcesCompat.getDrawable(resources, R.drawable.correct_mark, null)!!
        wrong = ResourcesCompat.getDrawable(resources, R.drawable.wrong_mark, null)!!

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
    * This function checks if the player got the tiles right, increments score by 10 / 20 pts.
    */
    private fun WinLoseConditions(){

        lifecycleScope.launch {

            if(userInputButtonList.containsAll(randomButtonList)){

                LevelUp()

                preferenceName = requireContext().getSharedPreferences("player_name", Context.MODE_PRIVATE)
                val name = preferenceName.getString("name", null)

                if(name != ""){
                    playerViewModel.updateScore(name.toString(), gameScore)
                }

                points.text = gameScore.toString()
                gameTiles.text = tiles.toString()
                markImage.setImageDrawable(correct)
                delay(1000)
                markImage.setImageDrawable(null)

                ReloadGame()
                StartGame()


            }

            else if(userInputButtonList.count() >= tiles && !userInputButtonList.containsAll(randomButtonList)){

                points.text = gameScore.toString()
                markImage.setImageDrawable(wrong)
                delay(1000)
                markImage.setImageDrawable(null)

                ReloadGame()
                StartGame()

            }

        }

    }

    // this function is used to increase number of tiles after the first 3 successful rounds
    private fun LevelUp(){

        if(gameScore <= 30){
            gameScore += 10
        }

        if(gameScore == 30){
            tiles++
        }

        if (gameScore == 120){
            tiles++
        }

        when{
            gameScore > 30 -> { gameScore += 20 }
        }


    }



}
