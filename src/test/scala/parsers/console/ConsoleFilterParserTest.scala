package parsers.console

import filters.point.{GrayscaleImageBrightnessFilter, GrayscaleImageInvertFilter}
import filters.rotation.GrayscaleImageRotationFilter
import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel

class ConsoleFilterParserTest extends AnyFunSuite{
  private def getImage: Image[GrayscalePixel] = {
    Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(44), GrayscalePixel(184)),
        Vector(GrayscalePixel(244), GrayscalePixel(100))
      )
    )
  }
  test("Adding no filter") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    val filter = parser.parse(Array("--image-random", "--export-console"))

    assert(filter.filter(getImage) == getImage)
  }

  test("Adding brightness filter without value") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    assertThrows[IllegalArgumentException] {
      val filter = parser.parse(Array("--image-random", "--export-console", "--brightness"))
    }
  }

  test("Adding brightness filter") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    val filter = parser.parse(Array("--image-random", "--export-console", "--brightness", "66"))

    val manualFilter = new GrayscaleImageBrightnessFilter(66)

    assert(filter.filter(getImage) == manualFilter.filter(getImage))
  }

  test("Adding invert filter without value") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    assertThrows[IllegalArgumentException] {
      val filter = parser.parse(Array("--image-random", "--export-console", "--invert"))
    }
  }

  test("Adding invert filter") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    val filter = parser.parse(Array("--image-random", "--export-console", "--invert", "220"))

    val manualFilter = new GrayscaleImageInvertFilter(220)

    assert(filter.filter(getImage) == manualFilter.filter(getImage))
  }

  test("Adding rotation filter without value") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    assertThrows[IllegalArgumentException] {
      val filter = parser.parse(Array("--image-random", "--export-console", "--rotate"))
    }
  }

  test("Adding rotation filter") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    val filter = parser.parse(Array("--image-random", "--export-console", "--rotate", "-180"))

    val manualFilter = new GrayscaleImageRotationFilter(-180)

    assert(filter.filter(getImage) == manualFilter.filter(getImage))
  }

  test("Adding multiple filter") {
    val parser = ConsoleFilterParser("--rotate", "--invert", "--brightness")
    val filter = parser.parse(Array("--image-random", "--export-console", "--rotate", "-270", "--invert", "240", "--brightness", "10"))

    var manuallyFiltered = GrayscaleImageRotationFilter(-270).filter(getImage)
    manuallyFiltered = GrayscaleImageInvertFilter(240).filter(manuallyFiltered)
    manuallyFiltered = GrayscaleImageBrightnessFilter(10).filter(manuallyFiltered)

    assert(filter.filter(getImage) == manuallyFiltered)
  }
}
