package filters.rotation

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel

class GrayscaleImageRotationFilterTest extends AnyFunSuite{
  private def getSquareImage: Image[GrayscalePixel] = {
    new Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(11), GrayscalePixel(240)),
        Vector(GrayscalePixel(66), GrayscalePixel(1)),
      )
    )
  }

  private def getRectangleImage: Image[GrayscalePixel] = {
    new Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(11), GrayscalePixel(240), GrayscalePixel(0)),
        Vector(GrayscalePixel(66), GrayscalePixel(1), GrayscalePixel(255)),
      )
    )
  }

  test("Rotate square image +90") {
    val imageToRotate = getSquareImage
    val rotatedImage = new GrayscaleImageRotationFilter(90).filter(imageToRotate)

    assert(imageToRotate.getWidth == rotatedImage.getHeight)
    assert(imageToRotate.getHeight == rotatedImage.getWidth)

    for (i <- 0 until imageToRotate.getHeight) {
      for (j <- 0 until imageToRotate.getWidth) {
        assert(imageToRotate.getPixel(i, j) == rotatedImage.getPixel(j, imageToRotate.getHeight - i - 1))
      }
    }
  }

  test("Rotate rectangle image +90") {
    val imageToRotate = getRectangleImage
    val rotatedImage = new GrayscaleImageRotationFilter(90).filter(imageToRotate)

    assert(imageToRotate.getWidth == rotatedImage.getHeight)
    assert(imageToRotate.getHeight == rotatedImage.getWidth)

    for (x <- 0 until imageToRotate.getWidth)
      for (y <- 0 until imageToRotate.getHeight)
        assert(imageToRotate.getPixel(x, y) ==  rotatedImage.getPixel(y, imageToRotate.getWidth - 1 - x))
  }

  test("Rotate +90 twice should be equal to +180 - square image") {
    val rotateTwice = getSquareImage
    val rotateOnce = getSquareImage

    val firstRotation = new GrayscaleImageRotationFilter(90).filter(rotateTwice)
    val firstImage = new GrayscaleImageRotationFilter(90).filter(firstRotation)

    val secondImage = new GrayscaleImageRotationFilter(180).filter(rotateOnce)

    assert(firstImage == secondImage)
  }

  test("Rotate +90 twice should be equal to +180 - rectangle image") {
    val rotateTwice = getRectangleImage
    val rotateOnce = getRectangleImage

    val firstRotation = new GrayscaleImageRotationFilter(90).filter(rotateTwice)
    val firstImage = new GrayscaleImageRotationFilter(90).filter(firstRotation)

    val secondImage = new GrayscaleImageRotationFilter(180).filter(rotateOnce)

    assert(firstImage == secondImage)
  }

  test("Rotate +360 should be equal to -3600 - square image") {
    val imageOne360 = getSquareImage
    val imageTen360 = getSquareImage

    val firstImage = new GrayscaleImageRotationFilter(360).filter(imageOne360)
    val secondImage = new GrayscaleImageRotationFilter(-3600).filter(imageTen360)

    assert(firstImage == secondImage)
  }

  test("Rotate +360 should be equal to -3600 - rectangle image") {
    val imageOne360 = getRectangleImage
    val imageTen360 = getRectangleImage

    val firstImage = new GrayscaleImageRotationFilter(360).filter(imageOne360)
    val secondImage = new GrayscaleImageRotationFilter(-3600).filter(imageTen360)

    assert(firstImage == secondImage)
  }

  test("Rotate -270 should be equal to +90 and then -360 - square image") {
    val imageOneRotation = getSquareImage
    val imageTwoRotations = getSquareImage

    val firstImage = new GrayscaleImageRotationFilter(-270).filter(imageOneRotation)
    val firstRotation = new GrayscaleImageRotationFilter(90).filter(imageTwoRotations)
    val secondImage = new GrayscaleImageRotationFilter(-360).filter(firstRotation)

    assert(firstImage == secondImage)
  }

  test("Rotate -270 should be equal to +90 and then -360 - rectangle image") {
    val imageOneRotation = getRectangleImage
    val imageTwoRotations = getRectangleImage

    val firstImage = new GrayscaleImageRotationFilter(-270).filter(imageOneRotation)
    val firstRotation = new GrayscaleImageRotationFilter(90).filter(imageTwoRotations)
    val secondImage = new GrayscaleImageRotationFilter(-360).filter(firstRotation)

    assert(firstImage == secondImage)
  }

  test("Should rotate only 90 times k degrees") {
    assertThrows[IllegalArgumentException] {
      val filter = new GrayscaleImageRotationFilter(47)
    }
    assertThrows[IllegalArgumentException] {
      val filter = new GrayscaleImageRotationFilter(11)
    }
    assertThrows[IllegalArgumentException] {
      val filter = new GrayscaleImageRotationFilter(-99)
    }
  }
}
