package com.sbarrido.midtermapp
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {
    val target = Random.nextInt(100) + 1
    var numGuess = 0
    var currGuess = 0
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
        }

        return "Found"
    }
    fun increment() {
        currGuess += 1
    }
    fun decrement() {
        currGuess -= 1
    }
}