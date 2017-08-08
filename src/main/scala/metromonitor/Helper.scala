/**
  * Created by awoizesko on 8/20/16.
  *
  * Set of utility functions
  */

package metromonitor

import org.json4s._
import org.json4s.native.JsonMethods._

import scala.io.Source._

object Helper {
  def filepathToString(fp: String): String = {
    val stream = getClass.getClassLoader.getResourceAsStream(fp)
    val output = fromInputStream(stream).mkString
    stream.close()
    output
  }

  def stringToMap(jsonData: String): Map[String, String] = {
    implicit val formats = DefaultFormats
    parse(jsonData).extract[Map[String, String]]
  }

  def loadPhrases(fp: String): Array[String] = {
    // load line delimited phrases
    this.filepathToString(fp).split("\n")
  }
}