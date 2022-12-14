package TinyImageFormatGenerator

import collection.mutable.StringBuilder
case class WriteZigCode(table: Seq[(String, GeneratorCode)]):
  val text = {
    var sb = StringBuilder()
    sb ++= "// Do Not Edit Autogenerated by tiny_image_format_generator\n\n"
    sb ++= "const TinyImageFormat = @import(\"tiny_image_format.zig\").Format;\n"
    genCodes(sb)

    sb.result()
  }

  def genCodes(sb: StringBuilder) =
    sb ++= "pub fn To(fmt: TinyImageFormat) u64 {\n"
    sb ++= "    switch(fmt) {\n"
    table.foreach(fmt => sb ++= f"        .${fmt._1} => return ${fmt._2.code}%#018x,\n")
    sb ++= "    }\n}\n\n"
    sb ++= "pub fn From(code: u64) TinyImageFormat {\n"
    sb ++= "    switch(code) {\n"
    table.foreach(fmt => sb ++= f"        ${fmt._2.code}%#018x => return .${fmt._1},\n")
    sb ++= "        else => return 0,\n"
    sb ++= "    }\n}\n\n"
