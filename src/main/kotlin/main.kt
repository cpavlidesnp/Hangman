var allWords = arrayListOf("abruptly", "absurd", "abyss", "affix", "askew", "avenue", "awkward", "axiom", "azure", "bagpipes", "bandwagon", "banjo", "bayou", "beekeeper", "bikini", "blitz", "blizzard", "boggle", "bookworm", "boxcar", "boxful", "buckaroo", "buffalo", "buffoon", "buxom", "buzzard", "buzzing", "buzzwords", "caliph", "cobweb", "cockiness", "croquet", "crypt", "curacao", "cycle", "daiquiri", "dirndl", "disavow", "dizzying", "duplex", "dwarves", "embezzle", "equip", "espionage", "exodus", "faking", "fishhook", "fixable", "fjord", "flapjack", "flopping", "fluffiness", "flyby", "foxglove", "frazzled", "frizzled", "fuchsia", "funny", "gabby", "galaxy", "galvanize", "gazebo", "gizmo", "glowworm", "glyph", "gnarly", "gnostic", "gossip", "grogginess", "haiku", "haphazard", "hyphen", "iatrogenic", "icebox", "injury", "ivory", "ivy", "jackpot", "jaundice", "jawbreaker", "jaywalk", "jazziest", "jazzy", "jelly", "jigsaw", "jinx", "jiujitsu", "jockey", "jogging", "joking", "jovial", "joyful", "juicy", "jukebox", "jumbo", "kayak", "kazoo", "keyhole", "khaki", "kilobyte", "kiosk", "kitsch", "kiwifruit", "klutz", "knapsack", "larynx", "lengths", "lucky", "luxury", "lymph", "marquis", "matrix", "megahertz", "microwave", "mnemonic", "mystify", "naphtha", "nightclub", "nowadays", "nymph", "onyx", "ovary", "oxidize", "oxygen", "pajama", "peekaboo", "phlegm", "pixel", "pizazz", "pneumonia", "polka", "pshaw", "psyche", "puppy", "puzzling", "quartz", "queue", "quips", "quixotic", "quiz", "quizzes", "quorum", "razzmatazz", "rhubarb", "rhythm", "rickshaw", "schnapps", "scratch", "shiv", "snazzy", "sphinx", "spritz", "squawk", "staff", "strength", "strengths", "stretch", "stronghold", "stymied")
var chosenWord: String = ""
var attempts: Int = 1
var failedAttempts: Int = 0
val maxAttempts: Int = 6
var isGameActive = true
var lettersFound = hashSetOf<String>()
var lettersMissed = hashSetOf<String>()

fun main (){
    newGame()

    var iteration = 0
    while (isGameActive) {
        iteration ++
        println(chosenWord)
        printWord(chosenWord, lettersFound)
        if (iteration>1){
            printMissed()
        }
        println("Please choose a letter. Attempt: $attempts -  Failed Attempts: $failedAttempts")
        val userGuess = readLine()?:""
        if (validateUserGuess(userGuess))
            checkGuess(userGuess, chosenWord)
        else
            println("Non-Attempt. Input is invalid (more than 1 letter)")

        if (checkWinner(lettersFound, chosenWord)){
            printWord(chosenWord, lettersFound)
            println("YOU FOUND THE WORD!! ==> $chosenWord")
            if (playAgain()){
                newGame()
            }else{
                isGameActive = false
                break
            }

        }


        if (checkAttempts()) continue else {
            printWord(chosenWord, lettersFound)
            printMissed()
            println("Failed Attempts: $failedAttempts")
            println("YOU HAVE BEEN HANGED")
            if (playAgain()){
                newGame()
            }else{
                isGameActive = false
                break
            }
        }

    }
}

fun newGame(){
    chosenWord = chooseWord()
    lettersFound = hashSetOf<String>()
    lettersMissed = hashSetOf<String>()
    failedAttempts = 0
    attempts = 1

    println("NEW GAME STARTED. GOOD LUCK!")

}

fun chooseWord(): String {
    val randomNum = (0..allWords.size).random() // generated random from 0 to 10 included

    return allWords[randomNum]
}

fun printWord(word:String, found: Set<String>){

    for (letter in word){
        if (found.contains(letter.toString())) print(" $letter ") else print(" _ ")
    }
    println()

}

fun printMissed(){
    println("Missed letters: $lettersMissed")
}

fun validateUserGuess(guess:String): Boolean{
    return when (guess.length){
        1 -> true
        else -> false
    }
}

fun checkGuess(guess:String, word:String){
    if (word.contains(guess))
        lettersFound.add(guess)
    else{
        lettersMissed.add(guess)
        failedAttempts++
    }
    attempts++
}

fun checkAttempts():Boolean{
    return (failedAttempts<maxAttempts)

}

fun checkWinner(userLetters: Set<String>, word:String): Boolean {

    var foundWinner = true
    for (element in word){
        if (userLetters.contains(element.toString()))
            continue
        else
            foundWinner = false
    }

    return foundWinner
}

fun playAgain(): Boolean{
    println("Would uou like to play again? y/n")
    val userInput = readLine()?:"n"
    return (userInput=="y" || userInput=="yes")


}