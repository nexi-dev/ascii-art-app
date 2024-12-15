package tables.nonlinear

import pixel.{ASCIIPixel, GrayscalePixel}
import tables.TransformationTable

/**
 * Transforms grayscale pixel to ASCII pixel in a nonlinear ammner
 * @param characterRamp the characters that correspond to specific intervals of gray intensity ranges
 */
class NonlinearTransformationTable(val characterRamp: String = " .:-=+*#%@") extends TransformationTable[GrayscalePixel, ASCIIPixel] {
  if (characterRamp.length > 256 || characterRamp.isEmpty) {
    throw IllegalArgumentException("Character ramps must be 1 - 256 chars")
  }

  /**
   * Transforms the grayscale pixel to ascii pixel
   * Linearly in the first half and leaves the other half for the last pixel
   * @param pixel to transform
   * @return transformed pixel
   */
  override def transform(pixel: GrayscalePixel): ASCIIPixel = {
    val charactersCount = characterRamp.length
    var index = (pixel.getPixel * (charactersCount - 1)) / 128

    if (pixel.getPixel >= 128) {
      index = charactersCount - 1
    }

    ASCIIPixel(characterRamp.charAt(index))
  }
}
