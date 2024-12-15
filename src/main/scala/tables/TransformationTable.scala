package tables

import pixel.Pixel

/**
 * Transforms pixel S to pixel T
 * @tparam S type of pixel to transform
 * @tparam T transformed pixel
 */
trait TransformationTable[S <: Pixel, T <: Pixel] {
  /**
   * Transforms pixel
   * @param pixel to transform
   * @return transformed pixel
   */
  def transform(pixel: S): T
}
