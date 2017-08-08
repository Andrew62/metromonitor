package metromonitor

import java.security.InvalidParameterException

class ArgParser(args: Array[String]) {
  val argList: List[String] = args.toList
  type OptionMap = Map[Symbol, Boolean]

  def nextOption(map: OptionMap, list: List[String]): OptionMap = {
    // this match doesn't throw an error if there are empty values...
    list match {
      case Nil => map
      case "--tweet" :: value :: tail => nextOption(map ++ Map('tweet -> value.toBoolean), tail)
      case "--test" :: value :: tail => nextOption(map ++ Map('test -> value.toBoolean), tail)
      case "--testEvent" :: value :: tail => nextOption(map ++ Map('testEvent -> value.toBoolean), tail)
      case _ => throw new InvalidParameterException("Invalid command line arg")
    }
  }
  val options: OptionMap = nextOption(Map(), argList)

}
