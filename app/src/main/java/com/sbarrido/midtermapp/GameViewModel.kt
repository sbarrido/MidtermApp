package com.sbarrido.midtermapp
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel(val dao: ScoreDAO) : ViewModel() {
    val target = Random.nextInt(100) + 1
    var numGuess = 0
    var currGuess = 0
    var playerName = ""

    fun setName(name: String) {
        playerName = name
    }
    fun makeGuess(guess: Int) : String {
        currGuess = guess
        if(currGuess < target) {
            numGuess += 1
            return "Guess is Lower"
        } else if (currGuess > target) {
            numGuess +=1
            return "Guess is Higher"
        } else {
            numGuess +=1
            addScore()
        }

        return "Found"
    }
    fun addScore() {
        viewModelScope.launch {
            val score = HighScore()
            score.scoreGuess = numGuess
            score.pname = playerName
            dao.insert(score)
        }
    }
    fun isWon() = currGuess == target
    fun increment() {
        currGuess += 1
    }
    fun decrement() {
        currGuess -= 1
    }
}