/**
  * Created by awoizesko on 7/24/16.
  */


import metromonitor._

import Helper._

object MetroMonitor {
  def main(args: Array[String]): Unit ={

    // Load key file from resources
    val keys = stringToMap(filepathToString("twitterkeys.json"))

    // Load metro URL from resources just so we don't have a long URL here
    val metroUrl  = filepathToString("metro-url.txt")

    // Create an object to check the metro feed
    val metroMonitor = new Metro(metroUrl)

    // send a tweet if there is a delay
    val chirper = new Chirper(keys)

    // Check wmata rss feed ang get an event
    val event = metroMonitor.getEvent()

    if (event.isEmpty == false){
      // get the line that's having issues
      val metroLine = event.get("title").get

      // drop it into a canned phrase. Could use more
      val phrase = chirper.catchPhrase(metroLine)

      // Tweet it!
      chirper.chirp(phrase)

      println(phrase)

    } else {
      println("No events")
    }
  }
}
