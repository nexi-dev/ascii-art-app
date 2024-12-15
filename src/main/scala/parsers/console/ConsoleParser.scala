package parsers.console

import parsers.Parser

/**
 * Parses array of strings from console input
 * @tparam T the result of parsing
 */
trait ConsoleParser[T] extends Parser[Array[String], T]{
  /**
   * Parses the array of string from console input
   * @param args the array of string from std input
   * @return the result of parsing
   */
  override def parse(args: Array[String]): T
}
