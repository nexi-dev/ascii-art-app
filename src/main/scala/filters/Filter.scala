package filters

/**
 * Filters something of type T
 * @tparam T the object to filter
 */
trait Filter[T] {
  def filter(toFilter: T): T
}
