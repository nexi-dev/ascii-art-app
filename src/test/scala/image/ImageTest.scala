package image

import pixel.{Pixel, GrayscalePixel, RGBPixel, ASCIIPixel}
import org.scalatest.funsuite.AnyFunSuite

class ImageTest extends AnyFunSuite {
  private def checkImage[T <: Pixel](image: Image[T], width: Int, height: Int, expectedPixels: Map[(Int, Int), T]): Unit = {
    assert(image.getWidth == width)
    assert(image.getHeight == height)

    expectedPixels.foreach {
      case ((i, j), expectedPixel) =>
        assert(image.getPixel(i, j) == expectedPixel)
    }
  }

  private def pixelsOutOfBounds[T <: Pixel](image: Image[T]): Unit = {
    assertThrows[IllegalArgumentException] {
      image.getPixel(image.getWidth, 0)
    }
    assertThrows[IllegalArgumentException] {
      image.getPixel(0, image.getHeight)
    }
    assertThrows[IllegalArgumentException] {
      image.getPixel(image.getWidth, image.getHeight)
    }
    assertThrows[IllegalArgumentException] {
      image.getPixel(-1, 0)
    }
    assertThrows[IllegalArgumentException] {
      image.getPixel(0, -1)
    }
    assertThrows[IllegalArgumentException] {
      image.getPixel(-1, -1)
    }
  }

  test("Load image of RGB pixels") {
    val red: RGBPixel = RGBPixel(255, 0, 0)
    val green: RGBPixel = RGBPixel(0, 255, 0)
    val blue: RGBPixel = RGBPixel(0, 0, 255)
    val image = Image[RGBPixel](
      Vector(
        Vector(red, green, blue),
        Vector(blue, red, green)
      )
    )

    checkImage(image, 2, 3, Map(
      (0, 0) -> red,
      (0, 1) -> green,
      (0, 2) -> blue,
      (1, 0) -> blue,
      (1, 1) -> red,
      (1, 2) -> green
    ))

    val anotherImage: Image[RGBPixel] = Image[RGBPixel](
      Vector(
        Vector(red, green, blue),
        Vector(blue, red, green)
      )
    )
    assert(image == anotherImage)

    pixelsOutOfBounds[RGBPixel](image)
  }

  test("Load image of grayscale pixels") {
    val zeroIntensityPixel: GrayscalePixel = GrayscalePixel(0)
    val halfIntensityPixel: GrayscalePixel = GrayscalePixel(128)
    val fullIntensityPixel: GrayscalePixel = GrayscalePixel(255)
    val image: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(zeroIntensityPixel, fullIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel)
      )
    )

    checkImage(image, 4, 2, Map(
      (0, 0) -> zeroIntensityPixel,
      (0, 1) -> fullIntensityPixel,
      (1, 0) -> halfIntensityPixel,
      (1, 1) -> zeroIntensityPixel,
      (2, 0) -> halfIntensityPixel,
      (2, 1) -> zeroIntensityPixel,
      (3, 0) -> halfIntensityPixel,
      (3, 1) -> zeroIntensityPixel
    ))

    val anotherImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(zeroIntensityPixel, fullIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel),
        Vector(halfIntensityPixel, zeroIntensityPixel)
      )
    )
    assert(image == anotherImage)

    pixelsOutOfBounds[GrayscalePixel](image)
  }

  test("Load an image of one Grayscale pixel") {
    val pixel: ASCIIPixel = ASCIIPixel('X')
    val image: Image[ASCIIPixel] = Image[ASCIIPixel](
      Vector(Vector(pixel))
    )

    checkImage[ASCIIPixel](image, 1, 1, Map(
      (0, 0) -> pixel
    ))

    val anotherImage: Image[ASCIIPixel] = Image[ASCIIPixel](
      Vector(Vector(pixel))
    )
    assert(image == anotherImage)

    pixelsOutOfBounds[ASCIIPixel](image)
  }

  test("Load incomplete image of RGB pixels") {
    val red: RGBPixel = RGBPixel(255, 0, 0)
    val green: RGBPixel = RGBPixel(0, 255, 0)
    val blue: RGBPixel = RGBPixel(0, 0, 255)

    assertThrows[IllegalArgumentException] {
      val image: Image[RGBPixel] = Image[RGBPixel](
        Vector(
          Vector(red, green, blue),
          Vector(blue, red),
          Vector(blue, red, red, red, green, green)
        )
      )
    }
  }

  test("Load an empty image") {
    assertThrows[IllegalArgumentException] {
      val image: Image[RGBPixel] = Image[RGBPixel](
        Vector(
          Vector(),
          Vector()
        )
      )
    }
    assertThrows[IllegalArgumentException] {
      val image: Image[RGBPixel] = Image[RGBPixel](
        Vector(
          Vector()
        )
      )
    }
    assertThrows[IllegalArgumentException] {
      val image: Image[RGBPixel] = Image[RGBPixel](
        Vector()
      )
    }
  }
}
