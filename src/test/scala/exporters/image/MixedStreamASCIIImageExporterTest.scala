package exporters.image

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.ASCIIPixel

import java.io.{ByteArrayOutputStream, File}
import scala.io.Source

class MixedStreamASCIIImageExporterTest extends AnyFunSuite{
  private def getImage: Image[ASCIIPixel] = {
    Image[ASCIIPixel](
      Vector(
        Vector(ASCIIPixel('A'), ASCIIPixel('B')),
        Vector(ASCIIPixel('C'), ASCIIPixel('D'))
      )
    )
  }

  test("Can export to byte stream and file at the same time") {
    val byteArrayOutputStream = new ByteArrayOutputStream()

    val image = getImage
    val exporter = new MixedStreamASCIIImageExporter(
      Seq(
        StreamASCIIImageExporter(byteArrayOutputStream),
        FileOutputASCIIImageExporter(new File("src/test/resources/output.txt"))
      )
    )
    exporter.output(image)

    val output = byteArrayOutputStream.toString("UTF-8").trim

    val source = Source.fromFile(new File("src/test/resources/output.txt"))
    val fileContent = source.getLines().toList
    source.close()

    val outputLines = output.split("\n").toList

    assert(fileContent == outputLines)
    assert(fileContent == List("AC", "BD"))
  }

  test("Mixed export with empty sequence does not break") {
    val image = getImage
    val exporter = new MixedStreamASCIIImageExporter(Seq())
    exporter.output(image)
  }
}
