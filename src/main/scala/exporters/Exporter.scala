package exporters

/**
 * Exports something of type T
 * @tparam T an object to export
 */
trait Exporter[-T] {
  def output(toExport: T): Unit
}
