/**
  * Created by awoizesko on 7/24/16.
  */


import metromonitor._

import Helper._

object MetroMonitor {
  def main(args: Array[String]): Unit ={

    // Read the first commandline arg. can only be true or false
    val test = if (args.isEmpty) false else args(0).toBoolean

    if (test == true){
      println("\n\nTesting\n\n")
    }

    // Load key file from resources
    val keys = stringToMap(filepathToString("twitterkeys.json"))

    // Load metro URL from resources just so we don't have a long URL here
    val metroUrl  = filepathToString("metro-url.txt")

    // Phrases stored in a line delimited file
    val phrases = loadPhrases("phrases.txt")

    // Create an object to check the metro feed
    val metroMonitor = new Metro(metroUrl)

    // send a tweet if there is a delay
    val chirper = new Chirper(keys, phrases)

    // if testing is true we'll just pull a canned event
    val event = if (test == true) metroMonitor.getTestEvent() else metroMonitor.getEvent()

    if (event.isEmpty == false){
      // get the line that's having issues
      val metroLine = event.get("title").get

      // drop it into a canned phrase. Could use more
      val phrase = chirper.catchPhrase(metroLine)

      if (test == false) {
        // Tweet it!
        chirper.chirp(phrase)
      }

      println(phrase)

    } else {
      println("No events")
    }
  }
}
