package importers

/**
 * Imports something
 * @tparam T the object to import
 */
trait Importer[+T] {
  def input(): T
}
