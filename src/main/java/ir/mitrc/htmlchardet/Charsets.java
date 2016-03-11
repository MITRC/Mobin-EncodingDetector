package ir.mitrc.htmlchardet;

/**
 * To support more charsets you may want to import some special charsets from: </br>
 * <a href="http://www.iana.org/assignments/character-sets/character-sets.xhtml">
 * Registered character encodings at IANA</a>
 * 
 */
public enum Charsets {

	// @formatter:off
	UTF_8("UTF-8"),
	UTF_16("UTF-16"),
	WINDOWS_1256("Windows-1256"),
	WINDOWS_1252("Windows-1252"),
	UTF_16BE("UTF-16BE"),
	UTF_16LE("UTF-16LE"),
	UTF_32BE("UTF-32BE"),
	UTF_32LE("UTF-32LE"),
	SHIFT_JIS("Shift_JIS"),
	ISO_2022_JP("ISO-2022-JP"),
	ISO_2022_CN("ISO-2022-CN"),
	ISO_2022_KR("ISO-2022-KR"),
	GB18030("GB18030"),
	EUC_JP("EUC-JP"),
	EUC_KR("EUC-KR"),
	BIG5("Big5"),
	ISO_8859_1("ISO-8859-1"),
	ISO_8859_2("ISO-8859-2"),
	ISO_8859_5("ISO-8859-5"),
	ISO_8859_6("ISO-8859-6"),
	ISO_8859_7("ISO-8859-7"),
	ISO_8859_8("ISO-8859-8"),
	ISO_8859_9("ISO-8859-9"),
	WINDOWS_1251("Windows-1251"),
	KOI8_R("KOI8-R"),
	IBM424_rtl("IBM424_rtl"),
	IBM424_ltr("IBM424_ltr"),
	IBM420_rtl("IBM420_rtl"),
	IBM420_ltr("IBM420_ltr"),
	// Added charsets
	UTF_32("UTF-32"),
	ISO_8859_15("ISO-8859-15"),
	GB2312("GB2312"),
	GBK("GBK"),
	// WINDOWS_31J("WINDOWS-31J"),
	US_ASCII("US-ASCII");
    // @formatter:on

	private final String charsetType;

	private Charsets(String charsetType) {
		this.charsetType = charsetType;
	}

	public String getValue() {
		return charsetType;
	}

	/**
	 * 
	 * @param charset
	 * @return
	 */
	public static boolean isValid(String charset) {
		if (charset == null || charset.isEmpty()) {
			return false;
		}
		for (Charsets normalCharset : Charsets.values()) {
			if (normalCharset.getValue().equalsIgnoreCase(charset)) {
				return true;
			}
		}
		return canonicalize(charset) != null ? true : false;
	}

	/**
	 * @see Charsets#canonicalize(String charset)
	 * @param charset
	 * @return normalized form of a given charset
	 */
	public static String normalize(String charset) {
		for (Charsets normalCharset : Charsets.values()) {
			if (normalCharset.getValue().equalsIgnoreCase(charset)) {
				return normalCharset.getValue();
			}
		}
		return canonicalize(charset);
	}

	/**
	 * @see Charsets#normalize(String charset)
	 * @param charset
	 * @return canonical form of the given charset
	 */
	private static String canonicalize(String charset) {
		// abnormal and informal charset names seen by our crawler ...
		if (charset.equalsIgnoreCase("UTF8")) {
			return Charsets.UTF_8.getValue();
		} else if (charset.equalsIgnoreCase("CP1251")) {
			return Charsets.WINDOWS_1251.getValue();
		} else if (charset.equalsIgnoreCase("CP1252")) {
			return Charsets.WINDOWS_1252.getValue();
		} else if (charset.equalsIgnoreCase("CP1256")) {
			return Charsets.WINDOWS_1256.getValue();
		} else if (charset.equalsIgnoreCase("ISO8859-1")) {
			return Charsets.ISO_8859_1.getValue();
		} else if (charset.equalsIgnoreCase("ZH-CN")) {
			return Charsets.GBK.getValue();
		} else if (charset.equalsIgnoreCase("SHIFT-JIS")
				|| charset.equalsIgnoreCase("SJIS")) {
			return Charsets.SHIFT_JIS.getValue();
		}
		// TODO: This list should be continued ...

		return null;
	}
}
