package importers.random

import image.Image
import importers.ImageImporter
import pixel.RGBPixel
import scala.util.Random

/**
 * Imports a random image of RGB pixels
 * @param source the source of randomness
 * @param minLen minimal length of image
 * @param maxLen maximal length of image
 */
class ImageRandomImporter(val source: Random, val minLen: Int = 20, val maxLen: Int = 50) extends ImageImporter {
  if (minLen <= 0 || maxLen <= 0)
    throw IllegalArgumentException("Lengths of image must be positive")

  if (maxLen <= minLen)
    throw IllegalArgumentException(s"Max len must be greater to min len: ${maxLen} < ${minLen}")

  /**
   * Imports the image
   * @return the randomly generated image
   */
  override def input(): Image[RGBPixel] = {
    val width = source.nextInt(maxLen - minLen) + minLen
    val height = source.nextInt(maxLen - minLen) + minLen

    val pixels = Array.ofDim[RGBPixel](width, height)

    for(x <- 0 until width) {
      for (y <- 0 until height) {
        pixels(x)(y) = RGBPixel((source.nextInt(256), source.nextInt(256), source.nextInt(256)))
      }
    }

    new Image[RGBPixel](pixels.map(row => row.toVector).toVector)
  }
}
