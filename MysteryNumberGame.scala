import scala.util.Random
import scala.io.StdIn
import scala.language.postfixOps

/*
 * 🎲 MysteryNumberGame - Où chaque chiffre cache un secret... sauras-tu le deviner ? 🕵️‍♂️✨
 */


object MysteryNumberGame extends App {

  import scala.util.Random

  // Definition of the Akinator Class
  class MysteryNumber() {

    // --- Mystery number declaration ---
    val mysteryNumber = Random.between(0, 10) // Value to guess
    //val mysteryNumber = 5 // Test value

    // Method that compares the sugestion value to the actual value
    def guessMethod(userChoice: Int, attempts: Int): Boolean = {

      if (userChoice == mysteryNumber && attempts >= 0) {
        println(s"\n🏆Bravo🏆 ! Le chiffre mystère était bien $mysteryNumber")

        attempts match {
          case 4 => println("Waouh, du premier coup ! Ta médaille : 🥇")
          case 3 => println("Super, tu es perspicace ! Ta médaille : 🥈")
          case 2 => println("Bravo, tu t'en est bien sorti ! Ta médaille : 🥉")
          case 1 => println("Bravo, tu m'as donné des suilleurs froides !")
          case 0 => println("😫 Olala, tu n'étais pas chaud là, allez, prends ta revenche !")
        }
        winGame += 1
        false
      } else if (attempts > 0) {
        println(s"Dommage petit génie, il te reste ❗$attempts❗tentatives! 🎰")
        true
      } else {
        println("\n😭😭😭 Game over ! Tu as perdu !")
        println("🥁🥁🥁 le nombre mystère était...")
        println(
          s"""
             |
             |$mysteryNumber
             |""".stripMargin)
        loseGame += 1
        false
      }
    }
  }

  // --- Initialization ---
  var akinator = new MysteryNumber()
  var continue = true
  var winGame = 0
  var loseGame = 0

  // --- Intro ---
  println(
    """
      |************************************
      |*             AKINATOR             *
      |************************************
      |
      | GUESS THE NUMBER OR LOSE THE GAME
      |""".stripMargin

  )
  println("⚙️Le nombre à deviner se trouve entre 0 et 10 ")

  // --- Main Loop ---
  var attempts = Random.between(3, 6) // Number of random attempts

  while (continue) {

    val choice = readLine("\nAlors petit devin, quel est le nombre mystère ? : ")
    var result = true
    // A check of the type of returned value
    choice.toIntOption match {
      case Some(choice) =>
        attempts -= 1 // Decrementing the attempts after each match
        result = akinator.guessMethod(choice, attempts) // Calling the guessMethod()
      case None =>
        println("⚠️ Ce n'est pas un nombre ! Essaie encore.")
    }



    // --- Replay method---
    if (!result) {
      val replay = readLine("\nVeux-tu rejouer? (o/n) : ").toLowerCase
      replay match {
        case "o" =>
          continue = true
          attempts = Random.between(3, 6)
          akinator = new MysteryNumber()
        case "n" =>
          println("A bientôt petit devin !")
          println(s"\n\n Bilan : $winGame victoire(s) - $loseGame défaite(s)")
          continue = false
        case _ =>
          println("Entrée invalide, fin de partie.")
          println(s"\n\n Bilan : $winGame victoire(s) - $loseGame défaite(s)")
          continue = false
      }
    }
  }

  // version 1.3

}