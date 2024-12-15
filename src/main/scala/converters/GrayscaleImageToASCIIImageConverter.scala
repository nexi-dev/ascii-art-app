package converters

import tables.TransformationTable
import pixel.{GrayscalePixel, ASCIIPixel}

/**
 * Converts grayscale image to ASCII art imgae
 * @param conversionTable a table determining how to map gray intensities onto ASCII symbols
 */
class GrayscaleImageToASCIIImageConverter(
                                           conversionTable: TransformationTable[GrayscalePixel, ASCIIPixel]
                                         ) extends ImageToImageConverter[GrayscalePixel, ASCIIPixel] {
  /**
   * Converts one grayscale pixel into one ascii pixel based on the transformation table
   * @param pixel to convert
   * @return converted pixel to ascii pixel
   */
  override protected def convertPixel(pixel: GrayscalePixel): ASCIIPixel = {
    conversionTable.transform(pixel)
  }
}