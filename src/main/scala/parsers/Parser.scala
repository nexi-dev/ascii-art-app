package parsers

/**
 * Parses S and returns T
 * @tparam S parsed object
 * @tparam T the result of parsing
 */
trait Parser[S, T] {
  /**
   * Parses arguments of type S
   * @param args the parsed object
   * @return the result of parsing
   */
  def parse(args: S): T
}
