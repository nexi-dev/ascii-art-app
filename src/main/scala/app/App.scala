package app

/**
 * All applications must inherit this trait
 */
trait App {
  /**
   * Runs the app
   */
  def run(): Unit
}
