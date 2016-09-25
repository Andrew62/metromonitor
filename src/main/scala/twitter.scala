/**
  * Created by awoizesko on 7/24/16.
  *
  * This object will send a tweet from the main arg.
  */

package metromonitor

import java.util.Random

import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder


class Chirper(keys: Map[String, String]) {

  // Load keys from file. Don't want to store them in code
  val consumerKey = keys.get("consumer-key").get
  val consumerSecret = keys.get("consumer-secret").get
  val accessToken = keys.get("access-token").get
  val tokenSecret = keys.get("token-secret").get

  /**
    * chirp: When a string is passed, it will be published
    * as a tweet to the user's account
    *
    * @param text: String to tweet
    */
  def chirp(text: String): Unit = {
    val configBuilder = new ConfigurationBuilder()
    configBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey(this.consumerKey)
      .setOAuthConsumerSecret(this.consumerSecret)
      .setOAuthAccessToken(this.accessToken)
      .setOAuthAccessTokenSecret(this.tokenSecret)
    val tweetFactory = new TwitterFactory(configBuilder.build())
    val twitter = tweetFactory.getInstance()

    twitter.updateStatus(text)
  }

  /**
    * catchPhrase: produces a canned complaint given a metro line
    * @param metroLine: String. Name of a metro line
    * @return a phrase
    */
  def catchPhrase(metroLine: String): String = {
    val cannedPhrases = Array(
      s"Get it together @wmata! Delays on the $metroLine line... #Metropocalypse #wmata",
      s"I was going to ride Metro but there's a problem with the $metroLine line @wmata #Metropocalypse #wmata",
      s"Stuck because of issues with the $metroLine line. Thanks @wmata #Metropocalypse #wmata",
      s"$metroLine line special today @wmata #Metropocalypse #wmata",
      s"@wmata just sitting here waiting for the $metroLine line... #wmata #Metropocalypse",
      s"@wmata <insert canned upset phrase> $metroLine line again #wmata #Metropocalypse",
      s"Where's Martin DiCaro? Issues with the $metroLine line #wmata #Metropocalypse",
      s"Problem with the $metroLine line. Taking the shoelace express #wmata #Metropocalypse",
      s"When you're waiting for the $metroLine line and it's delayed... @wmata #wmata",
      s"@wmata Here we go again with the $metroLine line #wmata #Metropocalypse",
      s"@wmata Somewhere someone is waiting too long for the $metroLine line #Metropocalypse",
      s"@wmata late again. #Metropocalypse #wmata"
    )
    val rand = new Random(System.currentTimeMillis())
    val randomIndex = rand.nextInt(cannedPhrases.length)
    return cannedPhrases(randomIndex)
  }
}