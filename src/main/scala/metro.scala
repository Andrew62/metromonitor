/**
  * Created by awoizesko on 8/13/16.
  *
  * A class to check metro status
  */

package metromonitor

import scala.xml._
import scalaj.http._
import scala.io.Source
import java.util.Random
import Helper.filepathToString


class Metro(url: String) {
  val metroUrl: String = url

  def getEvent(): Map[String, String] = {
    // get data from WMATA
    val response: HttpResponse[String] = Http(this.metroUrl).asString

    // clean up the raw xml so it can be parsed
    val cleanedResponse = response.body.replaceAll("[^\\x20-\\x7e]", "")

    // Parse xml and split on "item" tag
    val items = XML.loadString(cleanedResponse) \\ "item"

    if (items.length > 0){
      val randomItem = this.getRandomItem(items)
      return this.parseItem(randomItem)
    } else {
      return Map()
    }
  }

  def getRandomItem(items: NodeSeq): Node = {
    // get a random item from input array
    val rand = new Random(System.currentTimeMillis())
    val randomIndex = rand.nextInt(items.length)
    return items(randomIndex)
  }

  def parseItem(item: Node): Map[String, String] = {

    // name of the metro line
    var title = ((item \ "title").text.trim)

    // sometimes there's more than one line in the status
    if (title.contains("\\")){
      title = title.split("\\")(0).trim
    } else if (title.contains(",")){
      title = title.split(",")(0).trim
    }

    // the line is all upper case initially so we do a simple
    // transform to make it title case
    title = title.toLowerCase.capitalize

    // parse the item into a key-value map
    return Map(
      "title"     -> title,
      "link"      -> (item \ "link").text.trim,
      "desc"      -> (item \ "description").text.trim,
      "source"    -> (item \ "source").text.trim,
      "pubdate"   -> (item \ "pubdate").text.trim,
      "guid"      -> (item \ "guid").text.trim
    )
  }

  def getTestEvent(): Map[String, String] = {
    // function used for testing. Loads cached xml
    val source = filepathToString("status.xml")

    // Parse xml and split on "item" tag
    val items = XML.loadString(source) \\ "item"

    if (items.length > 0){
      val randomItem = this.getRandomItem(items)

      return this.parseItem(randomItem)
    } else {
      return Map()
    }

  }
}
