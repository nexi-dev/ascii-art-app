package tables.linear

import pixel.{ASCIIPixel, GrayscalePixel}
import tables.TransformationTable

/**
 * Transforms the grayscale pixel to ascii pixel linearly
 * @param characterRamp the ascii symbols that correspond to each interval in order
 */
class LinearTransformationTable(val characterRamp: String = " .:-=+*#%@") extends TransformationTable[GrayscalePixel, ASCIIPixel] {
  if (characterRamp.length > 256 || characterRamp.isEmpty) {
    throw IllegalArgumentException("Character ramps must be 1 - 256 chars")
  }

  /**
   * Tranforms grayscale pixel to ascii pixel based on its intensity and its place in character ramp
   * @param pixel to transform
   * @return transformed pixel
   */
  override def transform(pixel: GrayscalePixel): ASCIIPixel = {
    val charactersCount = characterRamp.length
    val index = (pixel.getPixel * charactersCount) / 256
    ASCIIPixel(characterRamp.charAt(index))
  }
}
