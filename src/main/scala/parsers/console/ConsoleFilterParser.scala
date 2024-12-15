package parsers.console

import filters.ImageFilter
import filters.mixed.GrayscaleImageMixedFilter
import filters.point.{GrayscaleImageBrightnessFilter, GrayscaleImageInvertFilter}
import filters.rotation.GrayscaleImageRotationFilter
import pixel.GrayscalePixel

/**
 * Parses console arguments regarding filters and builds the filter
 * @param rotateKeyword the keyword corresponding to rotation filter
 * @param invertKeyword the keyword corresponding to invert filter
 * @param brightnessKeyword the keyword corresponding to brightness filter
 */
class ConsoleFilterParser(
                           rotateKeyword: String,
                           invertKeyword: String,
                           brightnessKeyword: String
                         ) extends ConsolePartialParser[GrayscaleImageMixedFilter] {
  /**
   * Parses array of strings from console and looks for keywords
   * @param args the array of string from std input
   * @return the mixed filter of all found filters
   */
  override def parse(args: Array[String]): GrayscaleImageMixedFilter = {
    val matches = findMatches(args, Array(rotateKeyword, invertKeyword, brightnessKeyword), 0, Int.MaxValue)

    val filterSeq: Seq[ImageFilter[GrayscalePixel]] = matches.map {
      case (filterKeyword, index) =>
        if (index + 1 >= args.length || args(index + 1).toIntOption.isEmpty) {
          throw new IllegalArgumentException(s"Missing value for filter: $filterKeyword")
        }
        filterKeyword match {
          case `rotateKeyword` => new GrayscaleImageRotationFilter(args(index + 1).toInt)
          case `invertKeyword` => new GrayscaleImageInvertFilter(args(index + 1).toInt)
          case `brightnessKeyword` => new GrayscaleImageBrightnessFilter(args(index + 1).toInt)
        }
    }
    new GrayscaleImageMixedFilter(filterSeq)
  }
}
