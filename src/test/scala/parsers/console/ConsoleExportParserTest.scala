package parsers.console

import exporters.image.MixedStreamASCIIImageExporter
import org.scalatest.funsuite.AnyFunSuite

class ConsoleExportParserTest extends AnyFunSuite{
  test("Can parse more same export types") {
    val parser = ConsoleExportParser("--export-file", "--export-console")
    val exporter = parser.parse(Array("--image-random", "--export-console", "--export-console", "--export-console"))

    assert(exporter.isInstanceOf[MixedStreamASCIIImageExporter])
  }

  test("Cannot export to file without path") {
    assertThrows[IllegalArgumentException] {
      val parser = ConsoleExportParser("--export-file", "--export-console")
      val exporter = parser.parse(Array("--image-random", "--export-file"))
    }
  }

  test("Can parse more different export types") {
    val parser = ConsoleExportParser("--export-file", "--export-console")
    val exporter = parser.parse(Array("--image-random", "--export-console", "--export-file", "path"))

    assert(exporter.isInstanceOf[MixedStreamASCIIImageExporter])
  }

  test("At least one export type must be in args") {
    assertThrows[IllegalArgumentException] {
      val parser = ConsoleExportParser("--export-file", "--export-console")
      val exporter = parser.parse(Array("--image-random"))
    }
  }
}
