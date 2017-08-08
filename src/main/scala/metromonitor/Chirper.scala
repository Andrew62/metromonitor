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
  val consumerKey: String = keys("consumer-key")
  val consumerSecret: String = keys("consumer-secret")
  val accessToken: String = keys("access-token")
  val tokenSecret: String = keys("token-secret")

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
    val randomIndex = rand.nextInt(this.phrases.length)
    val phrase = this.phrases(randomIndex)
    phrase.format(metroLine)
  }
}