package TinyImageFormatGenerator

object DepthStencilFormatTable:
  // shortcuts to help keep the table more readable
  def TotalSize(ts: Int) = ts match
    case 8  => DepthStencilTotalSize._8
    case 16 => DepthStencilTotalSize._16
    case 32 => DepthStencilTotalSize._32
    case 64 => DepthStencilTotalSize._64
    case _  => println(s"Depth Stencil Total Size is invalid"); DepthStencilTotalSize._8

  def BitCount(ts: Int) = ts match
    case 0  => DepthStencilBitCount._0
    case 8  => DepthStencilBitCount._8
    case 16 => DepthStencilBitCount._16
    case 24 => DepthStencilBitCount._24
    case 32 => DepthStencilBitCount._32
    case _  => println(s"Depth Stencil Bit Count is invalid"); DepthStencilBitCount._0

  val D = DepthStencilSwizzle.Depth
  val S = DepthStencilSwizzle.Stencil
  val Const0 = DepthStencilSwizzle.Const0

  val UNorm = DepthStencilType.UNorm
  val UInt = DepthStencilType.UInt
  val SFloat = DepthStencilType.SFloat
  val NoChannel = DepthStencilChannel(D, BitCount(0), UNorm)

  def C(swizzle: DepthStencilSwizzle, bitCount: Int, packType: DepthStencilType) =
    DepthStencilChannel(swizzle, BitCount(bitCount), packType)

  def D1(name: String, totalSize: Int, c0: DepthStencilChannel) =
    (name, GeneratorCode.DepthStencil(TotalSize(totalSize), c0, NoChannel))
  def D2(name: String, totalSize: Int, c0: DepthStencilChannel, c1: DepthStencilChannel) =
    (name, GeneratorCode.DepthStencil(TotalSize(totalSize), c0, c1))

  def Table = Seq(
    D1("D16_UNORM", 16, C(D, 16, UNorm)),
    D2("X8_D24_UNORM", 32, C(Const0, 8, UInt), C(D, 24, UNorm)),
    D1("D32_SFLOAT", 32, C(D, 32, SFloat)),
    D1("S8_UINT", 8, C(S, 8, UInt)),
    D2("D16_UNORM_S8_UINT", 32, C(D, 16, UNorm), C(S, 8, UInt)),
    D2("D24_UNORM_S8_UINT", 32, C(D, 24, UNorm), C(S, 8, UInt)),
    D2("D32_SFLOAT_S8_UINT", 64, C(D, 32, SFloat), C(S, 8, UInt)),
  )
