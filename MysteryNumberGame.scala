/*
* MYSTERY NUMBER GAME
*/

object Akinator extends App {

  import scala.util.Random

  // Definition of the Akinator Class
  class Akinator() {

    // --- Mystery number declaration ---
    val mysteryNumber = Random.between(0, 10) // valeur à deviner

    // Méthode qui compare la suggestion à la valeure réelle
    def deviner(userChoice: Int, attempts: Int): Boolean = {
      if (userChoice == mysteryNumber && attempts > 0) {
        println(s"\n🏆Bravo🏆 ! Le chiffre mystère était bien $mysteryNumber")

        attempts match {
          case 4 => println("Waouh, du premier coup ! Ta médaille : 🥇")
          case 3 => println("Super, tu es perspicace ! Ta médaille : 🥈")
          case 2 => println("Bravo, tu t'en est bien sorti ! Ta médaille : 🥉")
          case 1 => println("😫 Olala, tu n'étais pas chaud là, allez, prends ta revenche !")
        }
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
            |
            |$mysteryNumber
            |""".stripMargin)
        false
      }
    }
  }

  // --- Initialization ---
  val akinator = new Akinator()
  var continuer = true
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
  var attempts = 5
  while (continuer) {
    val choice = readLine("\nAlors petit devin, quel est le nombre mystère ? : ").toInt
    attempts -= 1 // Decrementing the attempts after each match
    val resultat = akinator.deviner(choice, attempts) // Call of deviner() method
    continuer = resultat
  }

}
