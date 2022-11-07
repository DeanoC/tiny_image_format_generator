package TinyImageFormatGenerator

object Etc2FormatTable:
  def Bits(bits: Int): EtcBits = bits match
    case 8  => EtcBits._8
    case 11 => EtcBits._11
    case _  => println("ETC bits invalid"); EtcBits._8

  val UNorm = EtcType.UNorm
  val SNorm = EtcType.SNorm
  val SRGB = EtcType.SRGB
  val NoAlpha = EtcAlpha.None
  val PunchThroughAlpha = EtcAlpha.PunchThrough
  val BlockAlpha = EtcAlpha.Block

  def D(name: String, etcType: EtcType, bits: Int, alpha: EtcAlpha, channelCount: Int) =
    (name, GeneratorCode.Etc(etcType, Bits(bits), alpha, channelCount))

  def Table = Seq(
    D("ETC2_R8G8B8_UNORM", UNorm, 8, NoAlpha, 3),
    D("ETC2_R8G8B8_SRGB", SRGB, 8, NoAlpha, 3),
    D("ETC2_R8G8B8A1_UNORM", UNorm, 8, PunchThroughAlpha, 4),
    D("ETC2_R8G8B8A1_SRGB", SRGB, 8, PunchThroughAlpha, 4),
    D("ETC2_R8G8B8A8_UNORM", UNorm, 8, BlockAlpha, 4),
    D("ETC2_R8G8B8A8_SRGB", SRGB, 8, BlockAlpha, 4),
    D("ETC2_EAC_R11_UNORM", UNorm, 11, NoAlpha, 1),
    D("ETC2_EAC_R11_SNORM", SNorm, 11, NoAlpha, 1),
    D("ETC2_EAC_R11G11_UNORM", UNorm, 11, NoAlpha, 2),
    D("ETC2_EAC_R11G11_SNORM", SNorm, 11, NoAlpha, 2),
  )
