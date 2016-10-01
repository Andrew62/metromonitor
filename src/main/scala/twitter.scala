/**
  * Created by awoizesko on 7/24/16.
  *
  * This object will send a tweet from the main arg.
  */

package metromonitor

import java.util.Random

import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder


class Chirper(keys: Map[String, String], phrases: Array[String]) {

  // Load keys from file. Don't want to store them in code
  val consumerKey = keys.get("consumer-key").get
  val consumerSecret = keys.get("consumer-secret").get
  val accessToken = keys.get("access-token").get
  val tokenSecret = keys.get("token-secret").get
  val inPhrases = phrases

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
    val rand = new Random(System.currentTimeMillis())
    val randomIndex = rand.nextInt(this.inPhrases.length)
    val phrase = this.inPhrases(randomIndex)
    return phrase.format(metroLine)
  }
}