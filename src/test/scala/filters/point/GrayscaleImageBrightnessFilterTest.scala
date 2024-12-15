package filters.point

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel

class GrayscaleImageBrightnessFilterTest extends AnyFunSuite {
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

  test("Check brightness filter does not change shape of square image") {
    val imageToFilter = getSquareImage
    val filteredImage = new GrayscaleImageBrightnessFilter(47).filter(imageToFilter)

    assert(imageToFilter.getHeight == filteredImage.getHeight)
    assert(imageToFilter.getWidth == filteredImage.getWidth)
  }

  test("Check brightness filter does not change shape of rectangle image") {
    val imageToFilter = getRectangleImage
    val filteredImage = new GrayscaleImageBrightnessFilter(47).filter(imageToFilter)

    assert(imageToFilter.getHeight == filteredImage.getHeight)
    assert(imageToFilter.getWidth == filteredImage.getWidth)
  }

  test("Test brightness should be identity if 0") {
    val imageToFilter = getSquareImage
    val filteredImage = new GrayscaleImageBrightnessFilter(0).filter(imageToFilter)

    assert(imageToFilter == filteredImage)
  }

  test("Test brightness should turn all pixels too 255 if 255") {
    val imageToFilter = getRectangleImage
    val filteredImage = new GrayscaleImageBrightnessFilter(255).filter(imageToFilter)

    assert(filteredImage == new Image[GrayscalePixel](Vector(
      Vector(GrayscalePixel(255), GrayscalePixel(255), GrayscalePixel(255)),
      Vector(GrayscalePixel(255), GrayscalePixel(255), GrayscalePixel(255))
    )))
  }

  test("Test brightness with value 84 on a square image") {
    val imageToFilter = getSquareImage
    val filteredImage = new GrayscaleImageBrightnessFilter(84).filter(imageToFilter)

    assert(filteredImage == new Image[GrayscalePixel](Vector(
      Vector(GrayscalePixel(255), GrayscalePixel(126)),
      Vector(GrayscalePixel(212), GrayscalePixel(85))
    )))
  }

  test("Test brightness with value 84 on a rectangle image") {
    val imageToFilter = getRectangleImage
    val filteredImage = new GrayscaleImageBrightnessFilter(84).filter(imageToFilter)

    assert(filteredImage == new Image[GrayscalePixel](Vector(
      Vector(GrayscalePixel(255), GrayscalePixel(126), GrayscalePixel(84)),
      Vector(GrayscalePixel(212), GrayscalePixel(85), GrayscalePixel(183))
    )))
  }
}
