package TinyImageFormatGenerator

import collection.mutable.StringBuilder

case class WriteZigQuery(table: Seq[(String, GeneratorCode)]):
  val text = {
    var sb = StringBuilder()
    sb ++= "// Do Not Edit Autogenerated by tiny_image_format_generator\n\n"
    sb ++= "const TinyImageFormat = @import(\"TinyImageFormat.zig\").TinyImageFormat\n"
    genCodes(sb)
    sb.result()
  }
  def genCodes(sb: StringBuilder) =
    sb ++= "fn Code(fmt: TinyImageFormat) u64 {\n"
    sb ++= "    switch(fmg) {\n"

    table.foreach(fmt => sb ++= s"    ${fmt._1} => ${fmt._2.code},\n")

    sb ++= "}\n}\n\n"
