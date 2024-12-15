package filters.mixed

import filters.point.{GrayscaleImageBrightnessFilter, GrayscaleImageInvertFilter}
import filters.rotation.GrayscaleImageRotationFilter
import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel

class GrayscaleImageMixedFilterTest extends AnyFunSuite {
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

  test("Empty mixed filter works like identity on rectangle images") {
    val image = getRectangleImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq()).filter(image)

    assert(image == filteredImage)
  }

  test("Empty mixed filter works like identity on square images") {
    val image = getSquareImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq()).filter(image)

    assert(image == filteredImage)
  }

  test("Mixed filter of rotations is equal to rotations being applied separately on square images") {
    val image = getSquareImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq(
      new GrayscaleImageRotationFilter(90),
      new GrayscaleImageRotationFilter(90)
    )).filter(image)

    var imageSeparateFilters = new GrayscaleImageRotationFilter(90).filter(image)
    imageSeparateFilters = new GrayscaleImageRotationFilter(90).filter(imageSeparateFilters)

    assert(filteredImage == imageSeparateFilters)
  }

  test("Mixed filter of rotations is equal to rotations being applied separately on rectangle images") {
    val image = getRectangleImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq(
      new GrayscaleImageRotationFilter(90),
      new GrayscaleImageRotationFilter(90)
    )).filter(image)

    var imageSeparateFilters = new GrayscaleImageRotationFilter(90).filter(image)
    imageSeparateFilters = new GrayscaleImageRotationFilter(90).filter(imageSeparateFilters)

    assert(filteredImage == imageSeparateFilters)
  }

  test("Mixed filter of brightnesses is equal to brightnesses being applied separately") {
    val image = getRectangleImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq(
      new GrayscaleImageBrightnessFilter(14),
      new GrayscaleImageBrightnessFilter(16)
    )).filter(image)

    var imageSeparateFilters = new GrayscaleImageBrightnessFilter(14).filter(image)
    imageSeparateFilters = new GrayscaleImageBrightnessFilter(16).filter(imageSeparateFilters)

    assert(filteredImage == imageSeparateFilters)
  }

  test("Mixed filter of inverts is equal to inverts being applied separately") {
    val image = getRectangleImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq(
      new GrayscaleImageInvertFilter(240),
      new GrayscaleImageInvertFilter(232),
    )).filter(image)

    var imageSeparateFilters = new GrayscaleImageInvertFilter(240).filter(image)
    imageSeparateFilters = new GrayscaleImageInvertFilter(232).filter(imageSeparateFilters)

    assert(filteredImage == imageSeparateFilters)
  }

  test("Mixed filter of -270 rotation, 13 brightness, 240 invert is equal to those filters applied separately") {
    val image = getRectangleImage
    val filteredImage = new GrayscaleImageMixedFilter(Seq(
      new GrayscaleImageRotationFilter(-270),
      new GrayscaleImageBrightnessFilter(13),
      new GrayscaleImageInvertFilter(240),
    )).filter(image)

    var imageSeparateFilters = new GrayscaleImageRotationFilter(-270).filter(image)
    imageSeparateFilters = new GrayscaleImageBrightnessFilter(13).filter(imageSeparateFilters)
    imageSeparateFilters = new GrayscaleImageInvertFilter(240).filter(imageSeparateFilters)

    assert(filteredImage == imageSeparateFilters)
  }
}
