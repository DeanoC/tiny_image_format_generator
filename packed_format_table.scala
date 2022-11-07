package TinyImageFormatGenerator

object PackedFormatTable:
  // shortcuts to help keep the table more readable
  val R = PackSwizzle.Red
  val G = PackSwizzle.Red
  val B = PackSwizzle.Red
  val A = PackSwizzle.Red
  val Const0 = PackSwizzle.Const0
  val Const1 = PackSwizzle.Const1

  val Single = PackSpecial.None
  val Multi2 = PackSpecial.Multi2
  val Multi4 = PackSpecial.Multi4
  val Multi8 = PackSpecial.Multi8
  val UNorm = PackType.UNorm
  val SNorm = PackType.SNorm
  val UInt = PackType.UInt
  val SInt = PackType.SInt
  val SRGB = PackType.SRGB
  val UFloat = PackType.UFloat
  val SFloat = PackType.SFloat
  val SBFloat = PackType.SBFloat
  val NoChannel = PackColour(PackSwizzle.Red, 0, PackType.None)

  def C(swizzle: PackSwizzle, bitCount: Int, packType: PackType) = PackColour(swizzle, bitCount, packType)
  def M1(name: String, special: PackSpecial, c0: PackColour) =
    (name, GeneratorCode.Pack(special, c0, NoChannel, NoChannel, NoChannel))
  def D1(name: String, c0: PackColour) = (name, GeneratorCode.Pack(Single, c0, NoChannel, NoChannel, NoChannel))
  def D2(name: String, c0: PackColour, c1: PackColour) =
    (name, GeneratorCode.Pack(Single, c0, c1, NoChannel, NoChannel))
  def D3(name: String, c0: PackColour, c1: PackColour, c2: PackColour) =
    (name, GeneratorCode.Pack(Single, c0, c1, c2, NoChannel))
  def D4(name: String, c0: PackColour, c1: PackColour, c2: PackColour, c3: PackColour) =
    (name, GeneratorCode.Pack(Single, c0, c1, c2, c3))

  def Table = Seq(
    // Multipack multiple pixel per byte formats
    M1("R1_UNORM", Multi8, C(R, 1, UNorm)),
    M1("R2_UNORM", Multi4, C(R, 2, UNorm)),
    M1("R4_UNORM", Multi2, C(R, 4, UNorm)),

    // 8 bit formats
    D2("R4G4_UNORM", C(R, 4, UNorm), C(G, 4, UNorm)),
    D2("G4R4_UNORM", C(G, 4, UNorm), C(R, 4, UNorm)),
    D1("A8_UNORM", C(A, 8, UNorm)),
    D1("R8_UNORM", C(R, 8, UNorm)),
    D1("R8_SNORM", C(R, 8, SNorm)),
    D1("R8_UINT", C(R, 8, UInt)),
    D1("R8_SINT", C(R, 8, SInt)),
    D1("R8_SRGB", C(R, 8, SRGB)),
    D3("B2G3R3_UNORM", C(B, 2, UNorm), C(G, 3, UNorm), C(R, 3, UNorm)),

    // 4 bit RGB[X|A]
    D4("R4G4B4A4_UNORM", C(R, 4, UNorm), C(G, 4, UNorm), C(B, 4, UNorm), C(A, 4, UNorm)),
    D4("R4G4B4X4_UNORM", C(R, 4, UNorm), C(G, 4, UNorm), C(B, 4, UNorm), C(Const1, 4, UNorm)),
    D4("B4G4R4A4_UNORM", C(B, 4, UNorm), C(G, 4, UNorm), C(R, 4, UNorm), C(A, 4, UNorm)),
    D4("B4G4R4X4_UNORM", C(B, 4, UNorm), C(G, 4, UNorm), C(R, 4, UNorm), C(Const1, 4, UNorm)),
    D4("A4R4G4B4_UNORM", C(A, 4, UNorm), C(R, 4, UNorm), C(G, 4, UNorm), C(B, 4, UNorm)),
    D4("X4R4G4B4_UNORM", C(Const1, 4, UNorm), C(R, 4, UNorm), C(G, 4, UNorm), C(B, 4, UNorm)),
    D4("A4B4G4R4_UNORM", C(A, 4, UNorm), C(B, 4, UNorm), C(G, 4, UNorm), C(R, 4, UNorm)),
    D4("X4B4G4R4_UNORM", C(Const1, 4, UNorm), C(B, 4, UNorm), C(G, 4, UNorm), C(R, 4, UNorm)),

    // 16 bit total formats
    D3("R5G6B5_UNORM", C(R, 5, UNorm), C(G, 6, UNorm), C(B, 5, UNorm)),
    D3("B5G6R5_UNORM", C(B, 5, UNorm), C(G, 6, UNorm), C(R, 5, UNorm)),
    D4("R5G5B5A1_UNORM", C(R, 5, UNorm), C(G, 5, UNorm), C(B, 5, UNorm), C(A, 1, UNorm)),
    D4("B5G5R5A1_UNORM", C(B, 5, UNorm), C(G, 5, UNorm), C(R, 5, UNorm), C(A, 1, UNorm)),
    D4("R5G5B5A1_UNORM", C(R, 5, UNorm), C(G, 5, UNorm), C(B, 5, UNorm), C(A, 1, UNorm)),
    D4("A1R5G5B5_UNORM", C(A, 1, UNorm), C(R, 5, UNorm), C(G, 5, UNorm), C(B, 5, UNorm)),
    D4("A1B5G5R5_UNORM", C(A, 1, UNorm), C(B, 5, UNorm), C(G, 5, UNorm), C(R, 5, UNorm)),
    D4("X1R5G5B5_UNORM", C(Const1, 1, UNorm), C(R, 5, UNorm), C(G, 5, UNorm), C(B, 5, UNorm)),
    D4("X1B5G5R5_UNORM", C(Const1, 1, UNorm), C(B, 5, UNorm), C(G, 5, UNorm), C(R, 5, UNorm)),
    D4("B2G3R3A8_UNORM", C(B, 2, UNorm), C(G, 3, UNorm), C(R, 2, UNorm), C(A, 8, UNorm)),
    D2("R8G8_UNORM", C(R, 8, UNorm), C(G, 8, UNorm)),
    D2("R8G8_SNORM", C(R, 8, SNorm), C(G, 8, SNorm)),
    D2("G8R8_UNORM", C(G, 8, UNorm), C(R, 8, UNorm)),
    D2("G8R8_SNORM", C(G, 8, SNorm), C(R, 8, SNorm)),
    D2("R8G8_UINT", C(R, 8, UInt), C(G, 8, UInt)),
    D2("R8G8_SINT", C(R, 8, SInt), C(G, 8, SInt)),
    D2("R8G8_SRGB", C(R, 8, SRGB), C(G, 8, SRGB)),
    D1("R16_UNORM", C(R, 16, UNorm)),
    D1("R16_SNORM", C(R, 16, SNorm)),
    D1("R16_UINT", C(R, 16, UInt)),
    D1("R16_SINT", C(R, 16, SInt)),
    D1("R16_SFLOAT", C(R, 16, SFloat)),
    D1("R16_SBFLOAT", C(R, 16, SBFloat)),

    // 24 bit formats
    D3("R8G8B8_UNORM", C(R, 8, UNorm), C(G, 8, UNorm), C(B, 8, UNorm)),
    D3("R8G8B8_SNORM", C(R, 8, SNorm), C(G, 8, SNorm), C(B, 8, SNorm)),
    D3("R8G8B8_UINT", C(R, 8, UInt), C(G, 8, UInt), C(B, 8, UInt)),
    D3("R8G8B8_SINT", C(R, 8, SInt), C(G, 8, SInt), C(B, 8, SInt)),
    D3("R8G8B8_SRGB", C(R, 8, SRGB), C(G, 8, SRGB), C(B, 8, SRGB)),
    D3("B8G8R8_UNORM", C(B, 8, UNorm), C(G, 8, UNorm), C(R, 8, UNorm)),
    D3("B8G8R8_SNORM", C(B, 8, SNorm), C(G, 8, SNorm), C(R, 8, SNorm)),
    D3("B8G8R8_UINT", C(B, 8, UInt), C(G, 8, UInt), C(R, 8, UInt)),
    D3("B8G8R8_SINT", C(B, 8, SInt), C(G, 8, SInt), C(R, 8, SInt)),
    D3("B8G8R8_SRGB", C(B, 8, SRGB), C(G, 8, SRGB), C(R, 8, SRGB)),

    // 32 bit format
    D4("R8G8B8A8_UNORM", C(R, 8, UNorm), C(G, 8, UNorm), C(B, 8, UNorm), C(A, 8, UNorm)),
    D4("R8G8B8A8_SNORM", C(R, 8, SNorm), C(G, 8, SNorm), C(B, 8, SNorm), C(A, 8, SNorm)),
    D4("R8G8B8A8_UINT", C(R, 8, UInt), C(G, 8, UInt), C(B, 8, UInt), C(A, 8, UInt)),
    D4("R8G8B8A8_SINT", C(R, 8, SInt), C(G, 8, SInt), C(B, 8, SInt), C(A, 8, SInt)),
    D4("R8G8B8A8_SRGB", C(R, 8, SRGB), C(G, 8, SRGB), C(B, 8, SRGB), C(A, 8, SRGB)),
    D4("B8G8R8A8_UNORM", C(B, 8, UNorm), C(G, 8, UNorm), C(R, 8, UNorm), C(A, 8, UNorm)),
    D4("B8G8R8A8_SNORM", C(B, 8, SNorm), C(G, 8, SNorm), C(R, 8, SNorm), C(A, 8, SNorm)),
    D4("B8G8R8A8_UINT", C(B, 8, UInt), C(G, 8, UInt), C(R, 8, UInt), C(A, 8, UInt)),
    D4("B8G8R8A8_SINT", C(B, 8, SInt), C(G, 8, SInt), C(R, 8, SInt), C(A, 8, SInt)),
    D4("B8G8R8A8_SRGB", C(B, 8, SRGB), C(G, 8, SRGB), C(R, 8, SRGB), C(A, 8, SRGB)),
    D4("R8G8B8X8_UNORM", C(R, 8, UNorm), C(G, 8, UNorm), C(B, 8, UNorm), C(Const1, 8, UNorm)),
    D4("B8G8R8X8_UNORM", C(B, 8, UNorm), C(G, 8, UNorm), C(R, 8, UNorm), C(Const1, 8, UNorm)),
    D2("R16G16_UNORM", C(R, 16, UNorm), C(G, 16, UNorm)),
    D2("G16R16_UNORM", C(G, 16, UNorm), C(R, 16, UNorm)),
    D2("R16G16_SNORM", C(R, 16, SNorm), C(G, 16, SNorm)),
    D2("G16R16_SNORM", C(G, 16, SNorm), C(R, 16, SNorm)),
    D2("R16G16_UINT", C(R, 16, UInt), C(G, 16, UInt)),
    D2("R16G16_SINT", C(R, 16, SInt), C(G, 16, SInt)),
    D2("R16G16_SFLOAT", C(R, 16, SFloat), C(G, 16, SFloat)),
    D2("R16G16_SBFLOAT", C(R, 16, SBFloat), C(G, 16, SBFloat)),
    D1("R32_UINT", C(R, 32, UInt)),
    D1("R32_SINT", C(R, 32, SInt)),
    D1("R32_SFLOAT", C(R, 32, SFloat)),
    D4("A2R10G10B10_UNORM", C(A, 2, UNorm), C(R, 10, UNorm), C(G, 10, UNorm), C(B, 10, UNorm)),
    D4("A2R10G10B10_SNORM", C(A, 2, SNorm), C(R, 10, SNorm), C(G, 10, SNorm), C(B, 10, SNorm)),
    D4("A2R10G10B10_UINT", C(A, 2, UInt), C(R, 10, UInt), C(G, 10, UInt), C(B, 10, UInt)),
    D4("A2R10G10B10_SINT", C(A, 2, SInt), C(R, 10, SInt), C(G, 10, SInt), C(B, 10, SInt)),
    D4("A2B10G10R10_UNORM", C(A, 2, UNorm), C(B, 10, UNorm), C(G, 10, UNorm), C(R, 10, UNorm)),
    D4("A2B10G10R10_SNORM", C(A, 2, SNorm), C(B, 10, SNorm), C(G, 10, SNorm), C(R, 10, SNorm)),
    D4("A2B10G10R10_UINT", C(A, 2, UInt), C(B, 10, UInt), C(G, 10, UInt), C(R, 10, UInt)),
    D4("A2B10G10R10_SINT", C(A, 2, SInt), C(B, 10, SInt), C(G, 10, SInt), C(R, 10, SInt)),
    D4("R10G10B10A2_UNORM", C(R, 10, UNorm), C(G, 10, UNorm), C(B, 10, UNorm), C(A, 2, UNorm)),
    D4("R10G10B10A2_SNORM", C(R, 10, SNorm), C(G, 10, SNorm), C(B, 10, SNorm), C(A, 2, SNorm)),
    D4("R10G10B10A2_UINT", C(R, 10, UInt), C(G, 10, UInt), C(B, 10, UInt), C(A, 2, UInt)),
    D4("R10G10B10A2_SINT", C(R, 10, SInt), C(G, 10, SInt), C(B, 10, SInt), C(A, 2, SInt)),
    D4("B10G10R10A2_UNORM", C(B, 10, UNorm), C(G, 10, UNorm), C(R, 10, UNorm), C(A, 2, UNorm)),
    D4("B10G10R10A2_SNORM", C(B, 10, SNorm), C(G, 10, SNorm), C(R, 10, SNorm), C(A, 2, SNorm)),
    D4("B10G10R10A2_UINT", C(B, 10, UInt), C(G, 10, UInt), C(R, 10, UInt), C(A, 2, UInt)),
    D4("B10G10R10A2_SINT", C(B, 10, SInt), C(G, 10, SInt), C(R, 10, SInt), C(A, 2, SInt)),
    D3("B10G11R11_UFLOAT", C(B, 10, UFloat), C(G, 11, UFloat), C(R, 11, UFloat)),
    D4("E5_UINT_B9G9R9_UFLOAT", C(A, 5, UInt), C(B, 9, UFloat), C(G, 9, UFloat), C(R, 9, UFloat)),

    // 48 bit formats
    D3("R16G16B16_UNORM", C(R, 16, UNorm), C(G, 16, UNorm), C(B, 16, UNorm)),
    D3("R16G16B16_SNORM", C(R, 16, SNorm), C(G, 16, SNorm), C(B, 16, SNorm)),
    D3("R16G16B16_UINT", C(R, 16, UInt), C(G, 16, UInt), C(B, 16, UInt)),
    D3("R16G16B16_SINT", C(R, 16, SInt), C(G, 16, SInt), C(B, 16, SInt)),
    D3("R16G16B16_SFLOAT", C(R, 16, SFloat), C(G, 16, SFloat), C(B, 16, SFloat)),
    D3("R16G16B16_SBFLOAT", C(R, 16, SBFloat), C(G, 16, SBFloat), C(B, 16, SBFloat)),

    // 64 bit formats
    D4("R16G16B16_UNORM", C(R, 16, UNorm), C(G, 16, UNorm), C(B, 16, UNorm), C(A, 16, UNorm)),
    D4("R16G16B16_SNORM", C(R, 16, SNorm), C(G, 16, SNorm), C(B, 16, SNorm), C(A, 16, SNorm)),
    D4("R16G16B16_UINT", C(R, 16, UInt), C(G, 16, UInt), C(B, 16, UInt), C(A, 16, UInt)),
    D4("R16G16B16_SINT", C(R, 16, SInt), C(G, 16, SInt), C(B, 16, SInt), C(A, 16, SInt)),
    D4("R16G16B16_SFLOAT", C(R, 16, SFloat), C(G, 16, SFloat), C(B, 16, SFloat), C(A, 16, SFloat)),
    D4("R16G16B16_SBFLOAT", C(R, 16, SBFloat), C(G, 16, SBFloat), C(B, 16, SBFloat), C(A, 16, SBFloat)),
    D2("R32G32_UINT", C(R, 32, UInt), C(G, 32, UInt)),
    D2("R32G32_SINT", C(R, 32, SInt), C(G, 32, SInt)),
    D2("R32G32_SFLOAT", C(R, 32, SFloat), C(G, 32, SFloat)),
    D1("R64_UINT", C(R, 64, UInt)),
    D1("R64_SINT", C(R, 64, SInt)),
    D1("R64_SFLOAT", C(R, 64, SFloat)),

    // 96 bit formats
    D3("R32G32B32_UINT", C(R, 32, UInt), C(G, 32, UInt), C(B, 32, UInt)),
    D3("R32G32B32_SINT", C(R, 32, SInt), C(G, 32, SInt), C(B, 32, SInt)),
    D3("R32G32B32_SFLOAT", C(R, 32, SFloat), C(G, 32, SFloat), C(B, 32, SFloat)),

    // 128 bit formats
    D4("R32G32B32A32_UINT", C(R, 32, UInt), C(G, 32, UInt), C(B, 32, UInt), C(A, 32, UInt)),
    D4("R32G32B32A32_SINT", C(R, 32, SInt), C(G, 32, SInt), C(B, 32, SInt), C(A, 32, SInt)),
    D4("R32G32B32A32_SFLOAT", C(R, 32, SFloat), C(G, 32, SFloat), C(B, 32, SFloat), C(A, 32, SFloat)),
    D2("R64G64_UINT", C(R, 64, UInt), C(G, 64, UInt)),
    D2("R64G64_SINT", C(R, 64, SInt), C(G, 64, SInt)),
    D2("R64G64_SFLOAT", C(R, 64, SFloat), C(G, 64, SFloat)),

    // 192 bit formats
    D3("R64G64B64_UINT", C(R, 64, UInt), C(G, 64, UInt), C(B, 64, UInt)),
    D3("R64G64B64_SINT", C(R, 64, SInt), C(G, 64, SInt), C(B, 64, SInt)),
    D3("R64G64B64_SFLOAT", C(R, 64, SFloat), C(G, 64, SFloat), C(B, 64, SFloat)),

    // 256 bit formats
    D4("R64G64B64A64_UINT", C(R, 64, UInt), C(G, 64, UInt), C(B, 64, UInt), C(B, 64, UInt)),
    D4("R64G64B64A64_SINT", C(R, 64, SInt), C(G, 64, SInt), C(B, 64, SInt), C(B, 64, SInt)),
    D4("R64G64B64A64_SFLOAT", C(R, 64, SFloat), C(G, 64, SFloat), C(B, 64, SFloat), C(B, 64, SFloat)),
  )
