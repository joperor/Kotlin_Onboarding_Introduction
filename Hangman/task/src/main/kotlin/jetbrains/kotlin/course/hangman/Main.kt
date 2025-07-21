package jetbrains.kotlin.course.hangman

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int): String = "Welcome to the game!$newLineSymbol$newLineSymbol" +
        "In this game, you need to guess the word made by the computer.$newLineSymbol" +
        "The hidden word will appear as a sequence of underscores, one underscore means one letter.$newLineSymbol" +
        "You have $maxAttemptsCount attempts to guess the word.$newLineSymbol" +
        "All words are English words, consisting of $wordLength letters.$newLineSymbol" +
        "Each attempt you should enter any one letter,$newLineSymbol" +
        "if it is in the hidden word, all matches will be guessed.$newLineSymbol$newLineSymbol" +
        "" +
        "For example, if the word \"CAT\" was guessed, \"_ _ _\" will be displayed first, " +
        "since the word has 3 letters.$newLineSymbol" +
        "If you enter the letter A, you will see \"_ A _\" and so on.$newLineSymbol$newLineSymbol" +
        "" +
        "Good luck in the game!"

// Returns a String that informs if the guess is contained or not
fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String {
    return if (!secret.contains(guess)) {
        println("Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord")
        currentUserWord
    } else {
        generateNewUserWord(secret, guess, currentUserWord).also {
            println("Great! This letter is in the word! The current word is $it")
        }
    }
}

/*fun safeUserInput(): Char {
    println("Please input your guess.")
    return generateSequence { safeReadLine() }
        .filter { isCorrectInput(it) }
        .first()
        .uppercase()[0]
}*/
fun safeUserInput(): Char {
    var guess: String
    do {
        println("Please input your guess.")
        guess = safeReadLine()
    } while (!isCorrectInput(guess))
    return guess.uppercase()[0]
}

// Check the input in the safeUserInput function
fun isCorrectInput(userInput: String): Boolean {
    return when {
        userInput.length != 1 -> { println("The length of your guess should be 1! Try again!"); false }
        !userInput[0].isLetter() -> { println("You should input only English letters! Try again!"); false }
        else -> true
    }
}

// Generates a new formated word with the new guess char
/*fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    return secret.indices
        .joinToString(separator = " ") { i ->
            when {
                secret[i] == guess -> guess
                currentUserWord.replace(separator, "")[i] != '_' -> currentUserWord.replace(separator, "")[i]
                else -> underscore
            }.toString()
        }
}*/
fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    val currentWordClean = currentUserWord.replace(separator, "")
    return secret.indices
        .joinToString(separator = separator) { i ->
            when {
                secret[i] == guess -> guess
                currentWordClean[i] != '_' -> currentWordClean[i]
                else -> underscore
            }.toString()
        }
}

// Make the first userWord with underscores
fun getHiddenSecret(wordLength: Int): String = List(wordLength) { underscore }.joinToString(separator = separator)

fun generateSecret(): String = words.random()

// Check winner guess
fun isComplete(secret: String, currentGuess: String): Boolean = secret == currentGuess.replace(separator, "")

// You will use this function later
fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

// You will use this function later
fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun playGame(secret: String, maxAttemptsCount: Int): Unit {
    var attempts = 0
    var currentGuess = getHiddenSecret(secret.length)
    var complete = false

    do {
        attempts++
        val guess = safeUserInput()
        currentGuess = generateNewUserWord(secret, guess, currentGuess)
        println(getRoundResults(secret, guess, currentGuess))
        complete = isComplete(secret, currentGuess)
    } while (!complete && attempts <= maxAttemptsCount)

    if (isWon(complete, attempts, maxAttemptsCount)) {
        println("Congratulations! You guessed it!")
    }
    if (isLost(complete, attempts, maxAttemptsCount)) {
        println("Sorry, you lost! My word is $secret")
    }
}

fun main() {
    println(getGameRules(wordLength, maxAttemptsCount))
    println(playGame(generateSecret(), maxAttemptsCount))
}