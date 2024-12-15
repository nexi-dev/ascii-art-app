# ASCII Art App

A Scala application that converts JPG, GIF, or PNG images into ASCII art. Import images, apply filters (e.g., rotation, brightness, invert), and choose linear or nonlinear pixel-to-ASCII conversion with customizable character ramps. Export ASCII art to console, a file, or both!

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)


# Installation

To use the app, you need to have Scala and sbt (Simple Build Tool) installed on your system.

```bash
git clone <URL>
cd ascii-art-app
```

Then, run sbt shell.

```bash
sbt
```

And you can run the app.

```bash
run <arguments>
```

# Usage

All the app keywords are customizable in the main method. The following examples use the default keywords to demonstrate functionality.

**Note:** The order of arguments is flexible. For instance, you can start by applying multiple filters (e.g., 20 in sequence), then specify multiple export targets (e.g., 3 files), define the image source, and continue adding more filters before finally exporting the result to the console.

## Importing an image

Generating a random image:
```bash
--image-random
```

Importing an image (only jpg, jpeg, gif, png extensions supported) from a file:
```bash
--image-file <path/to/file>
```

## Applying filters

Rotate the image by any multiple of 90 degrees. Negative values rotate clockwise, and positive values rotate anticlockwise:
```bash
--rotate <value>
```

Adjust the brightness of an image. Provide a value (positive or negative) that will be added to each pixel's intensity (range 0 - 255):
```bash
--brightness <value>
```

Invert the pixel intensity based on a specified maximum value (e.g., 255 for white):
```bash
--invert <value>
```

## ASCII conversion tables

Use the default linear conversion with Bourke's character ramp (```" .:-=+*#%@"```):
```bash
--table bourke
```

Specify your own character ramp for linear conversion. Use quotes (```""```) if the ramp includes spaces or special characters:
```bash
--table <character_ramp>
```

Use a nonlinear transformation table for ASCII conversion:
```bash
--table nonlinear
```

## Exporting

Image can be exported to more than one target place.

Save the ASCII art to a file:
```bash
--export-file <path/to/file>
```

Display the ASCII art in the terminal:
```
--export-console
```

# Examples

Generate a random file, rotate it 90 degrees clockwise, invert it, increase the brighntess by 10, export to terminal and files "file1.txt", file2.txt" while using a nonlinear ASCII conversion:
```bash
run --image-random --rotate -90 --invert 255 --brighntess 10 --export-console --export-file "file1.txt" --export-file "file2.txt" --table nonlinear
```

Import image from a path ```/examples/scala.jpg```, rotate it by 90 degrees clockwise and 90 degrees anticlockwise, then use a linear custom character ramp ``` .';/=@#``` and print it twice in the terminal:
```
run --image-file /examples/scala.jpg --rotate -90 --rotate 90 --table " .';/=@#" --export-console --export-console
```
