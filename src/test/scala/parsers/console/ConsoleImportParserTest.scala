package parsers.console

import importers.ImageImporter
import org.scalatest.funsuite.AnyFunSuite

class ConsoleImportParserTest extends AnyFunSuite {
  test("Cannot import from 0 sources") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    assertThrows[IllegalArgumentException] {
      val importer = parser.parse(Array("--export-console"))
    }
  }

  test("Cannot import from 2 or more sources") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    assertThrows[IllegalArgumentException] {
      val importer = parser.parse(Array("--image-file", "path", "--image-random", "--export-console"))
    }
  }

  test("Can import from random source") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    val importer = parser.parse(Array("--image-random", "--export-console"))

    assert(importer.isInstanceOf[ImageImporter])
  }

  test("Can import from file path source") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    val importer = parser.parse(Array("--image-file", "path.png", "--export-console"))

    assert(importer.isInstanceOf[ImageImporter])
  }

  test("Cannot import from file path source with bad extension") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    assertThrows[IllegalArgumentException] {
      val importer = parser.parse(Array("--image-file", "path.ggg", "--export-console"))
    }
  }

  test("Cannot import from file without path") {
    val parser = ConsoleImportParser("--image-file", "--image-random")
    assertThrows[IllegalArgumentException] {
      val importer = parser.parse(Array("--image-file"))
    }
  }
}
