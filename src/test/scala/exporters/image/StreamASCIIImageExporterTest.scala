package exporters.image

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.ASCIIPixel

import java.io.ByteArrayOutputStream

class StreamASCIIImageExporterTest extends AnyFunSuite{
  private def getImage: Image[ASCIIPixel] = {
    Image[ASCIIPixel](
      Vector(
        Vector(ASCIIPixel('A'), ASCIIPixel('B')),
        Vector(ASCIIPixel('C'), ASCIIPixel('D'))
      )
    )
  }

  test("Check the output stream is correct") {
    val image = getImage
    val byteArrayOutputStream = new ByteArrayOutputStream()

    val exporter = new StreamASCIIImageExporter(byteArrayOutputStream)
    exporter.output(image)

    val output = byteArrayOutputStream.toString("UTF-8")
    val expectedOutput = "AC\nBD\n"

    assert(output == expectedOutput)
  }

  test("Once the stream is closed it cannot be output to") {
    val image = getImage
    val byteArrayOutputStream = new ByteArrayOutputStream()

    val exporter = new StreamASCIIImageExporter(byteArrayOutputStream)

    exporter.close()

    assertThrows[Exception] {
      exporter.output(image)
    }
  }
}
