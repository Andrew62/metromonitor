/**
  * Created by awoizesko on 7/24/16.
  */


import metromonitor.Helper._
import java.security.InvalidParameterException
import metromonitor.{ArgParser, Chirper, MetroMonitor}

object Monitor extends App {
  try {
    val cmdArgs = new ArgParser(args)
    if (cmdArgs.options.getOrElse('test, false)) println("**Testing**")
    // Load key file from resources
    val keys = stringToMap(filepathToString("twitterkeys.json"))
    // Load metro URL from resources just so we don't have a long URL here
    val metroUrl  = filepathToString("metro-url.txt")
    // Phrases stored in a line delimited file
    val phrases = loadPhrases("phrases.txt")
    // Create an object to check the metro feed
    val metroMonitor = new MetroMonitor(metroUrl)
    // send a tweet if there is a delay
    val chirper = new Chirper(keys, phrases)
    // if testing is true we'll just pull a canned event
    val event = if (cmdArgs.options.getOrElse('testEvent, true)){
      metroMonitor.getTestEvent
    } else {
      metroMonitor.getEvent
    }
    if (event.nonEmpty){
      // get the line that's having issues
      val metroLine = event("title")
      // drop it into a canned phrase. Could use more
      val phrase = chirper.catchPhrase(metroLine)
      // Tweet it!
      if (cmdArgs.options.getOrElse('tweet, true)) chirper.chirp(phrase)
      println(phrase)
    } else {
      println("No events")
    }
  } catch {
    case _ : InvalidParameterException => {
      val usage: String =
        """
          |Usage Monitor [--test bool] [--tweet bool] [--testEvet bool]
        """.stripMargin
      println(usage)
      System.exit(1)
    }
  }
}
