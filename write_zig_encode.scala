package TinyImageFormatGenerator

import collection.mutable.StringBuilder

case class WriteZigEncode(table: Seq[(String, GeneratorCode)]):
  val text = {
    var sb = StringBuilder()
    sb ++= "// Do Not Edit Autogenerated by tiny_image_format_generator\n\n"
    sb ++= "const TinyImageFormat = @import(\"tiny_image_format.zig\").Format;\n"
    sb ++= "const std = @import(\"std\");\n"
    sb ++= "\n"

    genFloatToSrgb(sb)

    val spaces = "            "
    genCanEncodeLogicalPixels(sb, 32, { (fmt, bitWidth) => fmt.PutPixel(bitWidth, spaces) })
    sb ++= "const Output = struct { plane0: []u8, plane1: []u8 = undefined };\n\n"
    genEncodeLogicalPixels(sb, 32, { (fmt, bitWidth) => fmt.PutPixel(bitWidth, spaces) })

    sb.result()
  }

  def genCanEncodeLogicalPixels(
      sb: StringBuilder,
      outputBitwidth: Int,
      func: (GeneratorCode, Int) => Option[String],
  ) =
    sb ++= s"pub fn CanEncodePixelsToF${outputBitwidth}(fmt: TinyImageFormat) bool {\n"
    sb ++= s"    switch (fmt) {\n"

    table.foreach(fmt => if func(fmt._2, outputBitwidth) != None then sb ++= f"        .${fmt._1} => return true,\n")

    sb ++= s"        else => return false,\n"
    sb ++= s"    }\n"
    sb ++= s"}\n\n"

  def genEncodeLogicalPixels(
      sb: StringBuilder,
      outputBitwidth: Int,
      func: (GeneratorCode, Int) => Option[String],
  ) =
    sb ++= s"pub fn EncodePixelsToF${outputBitwidth}(fmt: TinyImageFormat, input: []@Vector(4, f${outputBitwidth}), output: Output) void {\n"
    sb ++= s"    switch (fmt) {\n"

    table.foreach(fmt =>
      if func(fmt._2, outputBitwidth) != None then
        sb ++= f"        .${fmt._1} => {\n${func(fmt._2, outputBitwidth).get}"
        sb ++= s"        },\n",
    )

    sb ++= s"        else => {},\n"
    sb ++= s"    }\n"
    sb ++= s"}\n"

  def genFloatToSrgb(sb: StringBuilder) =
    sb ++= """
// from D3DX_DXGIFormatConvert.inl
fn f32ToSrgb(in: f32) u8 {
    var v = in;
    if (v < 0.0031308) {
        v *= 12.92;
    } else {
        v = 1.055 * std.math.pow(@TypeOf(v), v, 1.0 / 2.4) - 0.055;
    }
    return @floatToInt(u8, v);
}
"""
