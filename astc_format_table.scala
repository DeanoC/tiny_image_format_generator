package TinyImageFormatGenerator

object AtscFormatTable:
  def Size(size: Int) = size match
    case 1  => AstcSize._1
    case 4  => AstcSize._4
    case 5  => AstcSize._5
    case 6  => AstcSize._6
    case 8  => AstcSize._8
    case 10 => AstcSize._10
    case 12 => AstcSize._12
    case _  => println("Atsc invalid size"); AstcSize._1

  val UNorm = AstcType.UNorm
  val SRGB = AstcType.SRGB

  def D(name: String, astcType: AstcType, bitWidth: Int, bitHeight: Int, bitDepth: Int) =
    (name, GeneratorCode.Astc(astcType, Size(bitWidth), Size(bitHeight), Size(bitDepth)))

  def Table = Seq(
    D("ASTC_4x4_UNORM", UNorm, 4, 4, 1),
    D("ASTC_4x4_SRGB", SRGB, 4, 4, 1),
    D("ASTC_5x4_UNORM", UNorm, 5, 4, 1),
    D("ASTC_5x4_SRGB", SRGB, 5, 4, 1),
    D("ASTC_5x5_UNORM", UNorm, 5, 5, 1),
    D("ASTC_5x5_SRGB", SRGB, 5, 5, 1),
    D("ASTC_6x5_UNORM", UNorm, 6, 5, 1),
    D("ASTC_6x5_SRGB", SRGB, 6, 5, 1),
    D("ASTC_6x6_UNORM", UNorm, 6, 6, 1),
    D("ASTC_6x6_SRGB", SRGB, 6, 6, 1),
    D("ASTC_8x5_UNORM", UNorm, 8, 5, 1),
    D("ASTC_8x5_SRGB", SRGB, 8, 5, 1),
    D("ASTC_8x8_UNORM", UNorm, 8, 8, 1),
    D("ASTC_8x8_SRGB", SRGB, 8, 8, 1),
    D("ASTC_10x5_UNORM", UNorm, 10, 5, 1),
    D("ASTC_10x5_SRGB", SRGB, 10, 5, 1),
    D("ASTC_10x6_UNORM", UNorm, 10, 6, 1),
    D("ASTC_10x6_SRGB", SRGB, 10, 6, 1),
    D("ASTC_10x8_UNORM", UNorm, 10, 8, 1),
    D("ASTC_10x8_SRGB", SRGB, 10, 8, 1),
    D("ASTC_10x10_UNORM", UNorm, 10, 10, 1),
    D("ASTC_10x10_SRGB", SRGB, 10, 10, 1),
    D("ASTC_12x10_UNORM", UNorm, 12, 10, 1),
    D("ASTC_12x10_SRGB", SRGB, 12, 10, 1),
    D("ASTC_12x12_UNORM", UNorm, 12, 12, 1),
    D("ASTC_12x12_SRGB", SRGB, 12, 12, 1),
  )
