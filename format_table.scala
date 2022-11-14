package TinyImageFormatGenerator

val FormatTable =
  Seq(("UNDEFINED", GeneratorCode.Undefined)) ++
    PackedFormatTable.Table ++
    DepthStencilFormatTable.Table ++
    DxtcFormatTable.Table ++
    PvrtcFormatTable.Table ++
    Etc2FormatTable.Table ++
    AtscFormatTable.Table ++
    ClutFormatTable.Table
