package converters

/**
 * Converts source S to target T
 * @tparam S source
 * @tparam T target
 */
trait Converter[S, T]  {
  /**
   * Converts source to target
   * @param source to be converted
   * @return the converted source to target
   */
  def convert(source: S): T
}
