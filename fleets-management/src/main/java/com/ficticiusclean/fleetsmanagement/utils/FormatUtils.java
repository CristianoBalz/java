package com.ficticiusclean.fleetsmanagement.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {

	public static String formatCurrency(Double value) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
		return nf.format(value);
	}

	public static String formatDecimal(Double value, int precision) {
		String zeros = "";
		for (int i = 0; i < precision; i++) {
			zeros += "0";
		}

		if (!zeros.equals("")) {
			zeros = "." + zeros;
		}

		return applyFormat("###,##0" + zeros, value);
	}

	private static String applyFormat(String format, Double value) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());

		DecimalFormat df = new DecimalFormat(format, dfs);
		if (format.split("\\.").length > 1) {
			double d = Math.round((value * Double.parseDouble("1" + format.split("\\.")[1] + ".0")))
					/ Double.parseDouble("1" + format.split("\\.")[1] + ".0");
			return df.format(d);
		}
		String v = df.format(Math.round((value * 1)) / 1);
		return v.equals("0") ? "0" : v;

	}
}
