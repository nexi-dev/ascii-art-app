package converters

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.{GrayscalePixel, RGBPixel}

class RGBImageToGrayscaleConverterTest extends AnyFunSuite {
  test("Convert RGB image to grayscale image") {
    val red: RGBPixel = RGBPixel(255, 0, 0)
    val green: RGBPixel = RGBPixel(0, 255, 0)
    val blue: RGBPixel = RGBPixel(0, 0, 255)

    val converter: RGBImageToGrayscaleConverter = new RGBImageToGrayscaleConverter()
    val rgbImage: Image[RGBPixel] = Image[RGBPixel](
      Vector(
        Vector(red, green),
        Vector(blue, blue)
      )
    )

    val convertedGrayscaleImage: Image[GrayscalePixel] = converter.convert(rgbImage)

    assert(rgbImage.getHeight == convertedGrayscaleImage.getHeight)
    assert(rgbImage.getWidth == convertedGrayscaleImage.getWidth)

    for (i <- 0 until rgbImage.getWidth) {
      for (j <- 0 until rgbImage.getHeight) {
        val (red, green, blue) = rgbImage.getPixel(i, j).getPixel
        assert(
          convertedGrayscaleImage.getPixel(i, j) == GrayscalePixel(((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt)
        )
      }
    }
  }

  test("Convert extreme valued (0/255) RGB pixel image to grayscale") {
    val rgbImage: Image[RGBPixel] = Image[RGBPixel](
      Vector(Vector(RGBPixel(255, 0, 255)))
    )
    val convertedImage: Image[GrayscalePixel] = new RGBImageToGrayscaleConverter().convert(rgbImage)
    assert(rgbImage.getWidth == convertedImage.getWidth)
    assert(rgbImage.getHeight == convertedImage.getHeight)
    assert(convertedImage.getPixel(0, 0) == GrayscalePixel(((0.3 * 255) + (0.11 * 255)).toInt))
  }

  test("Convert RGB image with the same values across all channels to grayscale for all values from 0 to 255") {
    val converter: RGBImageToGrayscaleConverter = new RGBImageToGrayscaleConverter()

    for (value <- 0 to 255) {
      val rgbImage: Image[RGBPixel] = Image[RGBPixel](
        Vector(Vector(RGBPixel(value, value, value)))
      )
      val convertedGrayscaleImage: Image[GrayscalePixel] = converter.convert(rgbImage)
      assert(convertedGrayscaleImage.getPixel(0, 0) == GrayscalePixel(value))
    }
  }
}
