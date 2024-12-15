package exporters.image

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.ASCIIPixel

import java.io.File
import scala.io.Source

class FileOutputASCIIImageExporterTest extends AnyFunSuite{
  private def getImage: Image[ASCIIPixel] = {
    Image[ASCIIPixel](
      Vector(
        Vector(ASCIIPixel('A'), ASCIIPixel('B')),
        Vector(ASCIIPixel('C'), ASCIIPixel('D'))
      )
    )
  }

  test("Export image to file and verify content") {
    val image = getImage

    val outputFile = new File("src/test/resources/output.txt")

    val exporter = new FileOutputASCIIImageExporter(outputFile)
    exporter.output(image)

    val source = Source.fromFile(outputFile)
    val fileContent = source.getLines().mkString("\n")
    val expectedContent = "AC\nBD"

    assert(fileContent == expectedContent)
    outputFile.delete()
    source.close()
  }
}
