/**
  * Created by awoizesko on 8/20/16.
  *
  * Set of utility functions
  */

package metromonitor

import org.json4s._
import scala.io.Source._
import org.json4s.native.JsonMethods._

object Helper {
  def filepathToString(fp: String): String = {
    val stream = getClass.getClassLoader.getResourceAsStream(fp)
    return fromInputStream(stream).mkString
  }

  def stringToMap(jsonData: String): Map[String, String] = {
    implicit val formats = DefaultFormats
    return parse(jsonData).extract[Map[String, String]]
  }
}