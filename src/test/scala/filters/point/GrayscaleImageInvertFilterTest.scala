package filters.point

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel

class GrayscaleImageInvertFilterTest extends AnyFunSuite{
  private def getSquareImage: Image[GrayscalePixel] = {
    new Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(255), GrayscalePixel(42)),
        Vector(GrayscalePixel(128), GrayscalePixel(1)),
      )
    )
  }

  private def getRectangleImage: Image[GrayscalePixel] = {
    new Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(255), GrayscalePixel(42), GrayscalePixel(0)),
        Vector(GrayscalePixel(128), GrayscalePixel(1), GrayscalePixel(99)),
      )
    )
  }

  test("Invert filter should not change shape of square image") {
    val filter = new GrayscaleImageInvertFilter(0)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(imageToFilter.getHeight == result.getHeight)
    assert(imageToFilter.getWidth == result.getWidth)
  }

  test("Invert filter should not change shape of rectangle image") {
    val filter = new GrayscaleImageInvertFilter(0)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(imageToFilter.getHeight == result.getHeight)
    assert(imageToFilter.getWidth == result.getWidth)
  }

  test("Invert filter on square image with value 0") {
    val filter = new GrayscaleImageInvertFilter(0)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(0)),
      )
    ))
  }

  test("Invert filter on rectangle image with value 0") {
    val filter = new GrayscaleImageInvertFilter(0)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(0), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(0), GrayscalePixel(0)),
      )
    ))
  }

  test("Invert filter on square image with value 255") {
    val filter = new GrayscaleImageInvertFilter(255)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(213)),
        Vector(GrayscalePixel(127), GrayscalePixel(254)),
      )
    ))
  }

  test("Invert filter on rectangle image with value 255") {
    val filter = new GrayscaleImageInvertFilter(255)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(213), GrayscalePixel(255)),
        Vector(GrayscalePixel(127), GrayscalePixel(254), GrayscalePixel(156)),
      )
    ))
  }

  test("Invert filter on square image with value 47") {
    val filter = new GrayscaleImageInvertFilter(47)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(5)),
        Vector(GrayscalePixel(0), GrayscalePixel(46)),
      )
    ))
  }

  test("Invert filter on rectangle image with value 47") {
    val filter = new GrayscaleImageInvertFilter(47)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(5), GrayscalePixel(47)),
        Vector(GrayscalePixel(0), GrayscalePixel(46), GrayscalePixel(0)),
      )
    ))
  }

  test("Invert filter on square image with value 400 (out of bounds)") {
    val filter = new GrayscaleImageInvertFilter(400)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(145), GrayscalePixel(255)),
        Vector(GrayscalePixel(255), GrayscalePixel(255)),
      )
    ))
  }

  test("Invert filter on rectangle image with value 400 (out of bounds)") {
    val filter = new GrayscaleImageInvertFilter(400)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(145), GrayscalePixel(255), GrayscalePixel(255)),
        Vector(GrayscalePixel(255), GrayscalePixel(255), GrayscalePixel(255)),
      )
    ))}

  test("Invert filter on square image with value -60 (negative)") {
    val filter = new GrayscaleImageInvertFilter(-60)
    val imageToFilter = getSquareImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(0)),
      )
    ))
  }

  test("Invert filter on rectangle image with value -60 (negative)") {
    val filter = new GrayscaleImageInvertFilter(-60)
    val imageToFilter = getRectangleImage
    val result = filter.filter(imageToFilter)

    assert(result == Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(0), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(0), GrayscalePixel(0)),
      )
    ))
  }
}
