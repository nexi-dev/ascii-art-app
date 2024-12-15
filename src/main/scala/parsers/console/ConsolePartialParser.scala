package parsers.console

/**
 * Parses part of array of strings from console input
 * @tparam T the result of parsing
 */
trait ConsolePartialParser[T] extends ConsoleParser[T] {
  /**
   * Find the correct keywords in arguments along with their position
   * @param args arguments to be parsed
   * @param keywords specific list of allowed keywords
   * @param minCount minimum amount of keywords to be found
   * @param maxCount maximum amount of keywords to be found
   * @return array of keywords founds and its index
   */
  protected def findMatches(args: Array[String], keywords: Array[String], minCount: Int, maxCount: Int): Array[(String, Int)] = {
    val matches = args.zipWithIndex.filter { case (argument, _) => keywords.contains(argument) }

    if (matches.length < minCount || matches.length > maxCount) {
      throw new IllegalArgumentException(s"Invalid amount of arguments, choose (min: ${minCount}, max: ${maxCount}): ${keywords.mkString(", ")}")
    }
    matches
  }
}
