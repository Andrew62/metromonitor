
import org.scalatest.FlatSpec
import metromonitor.ArgParser
import java.security.InvalidParameterException

class ArgParserTest extends FlatSpec {

  "A Map" should "hold the proper command line args" in {
    val inputs = Array[String]("--test", "true", "--tweet", "false")
    val parser = new ArgParser(inputs)
    assert(parser.options('test) == true)
    assert(parser.options('tweet) == false)
    println("[ArgParser] Passed test with args")

  }

  "A Map" should "not fail if no args are passed" in {
    val inputs = Array[String]()
    val parser = new ArgParser(inputs)
    assert(parser.options.keys.isEmpty)
    println("[ArgParser] Passed test without args")
  }

  "A Map" should "only have one key" in {
    val inputOne = Array[String]("--test", "true")
    val parser = new ArgParser(inputOne)
    assert(parser.options('test) == true)
    assertThrows[NoSuchElementException]{
      parser.options('tweet)
    }
    println("[ArgParser] passing single arg")
  }

  it should "throw InvalidParameterException if an invalid arg is passed" in {
    val input = Array[String]("-test", "false")
    assertThrows[InvalidParameterException]{
      val parser = new ArgParser(input)
    }
    println("[ArgParser] passed invalid arg check")
  }
}
