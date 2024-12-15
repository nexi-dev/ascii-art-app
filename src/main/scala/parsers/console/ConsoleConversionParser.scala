package parsers.console

import converters.GrayscaleImageToASCIIImageConverter
import tables.linear.LinearTransformationTable
import tables.nonlinear.NonlinearTransformationTable

/**
 *
 * @param tableKeyword keyword corresponding to the table choosing
 * @param bourkeOption keyword corresponding to the implicit bourke character ramp
 * @param nonlinearOption keyword corresponding to the nonlinear option of filter
 */
class ConsoleConversionParser(
                               tableKeyword: String,
                               bourkeOption: String,
                               nonlinearOption: String
                             ) extends ConsolePartialParser[GrayscaleImageToASCIIImageConverter] {
  /**
   * Parses the arguments and builds the converter with the correct transformation table
   * @param args the array of string from std input
   * @return the result of parsing
   */
  override def parse(args: Array[String]): GrayscaleImageToASCIIImageConverter = {
    val matches = findMatches(args, Array(tableKeyword), 0, 1)
    if (matches.length == 0) {
        return new GrayscaleImageToASCIIImageConverter(new LinearTransformationTable())
    }

    val (tableType, index) = matches.head
    if (index + 1 >= args.length) {
      throw new IllegalArgumentException(s"Missing option for table: $tableKeyword")
    }
    val option = args(index + 1)

    option match {
      case `bourkeOption` =>
        new GrayscaleImageToASCIIImageConverter(new LinearTransformationTable())
      case `nonlinearOption` =>
        new GrayscaleImageToASCIIImageConverter(new NonlinearTransformationTable())
      case _ =>
        if (option.length < 5) {
          throw IllegalArgumentException(s"User defined transformation table must have at least 5 values instead got: $option.length")
        }
        new GrayscaleImageToASCIIImageConverter(new LinearTransformationTable(option))
    }
  }
}
