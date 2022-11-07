package TinyImageFormatGenerator

object PvrtcFormatTable:
  // shortcuts to help keep the table more readable
  def Bits(bits: Int): PvrtcBits = bits match
    case 2 => PvrtcBits._2
    case 4 => PvrtcBits._4
    case _ => println("Pvrtc bits invalid"); PvrtcBits._2

  val V1 = PvrtcVersion.V1
  val V2 = PvrtcVersion.V2
  val UNorm = PvrtcType.UNorm
  val SRGB = PvrtcType.SRGB

  def D(name: String, pvrtcType: PvrtcType, version: PvrtcVersion, bits: Int) =
    (name, GeneratorCode.Pvrtc(pvrtcType, version, Bits(bits)))

  def Table = Seq(
    D("PVRTC1_2BPP_UNORM", UNorm, V1, 2),
    D("PVRTC1_4BPP_UNORM", UNorm, V1, 4),
    D("PVRTC2_2BPP_UNORM", UNorm, V2, 2),
    D("PVRTC2_4BPP_UNORM", UNorm, V2, 4),
    D("PVRTC1_2BPP_SRGB", SRGB, V1, 2),
    D("PVRTC1_4BPP_SRGB", SRGB, V1, 4),
    D("PVRTC2_2BPP_SRGB", SRGB, V2, 2),
    D("PVRTC2_4BPP_SRGB", SRGB, V2, 4),
  )
