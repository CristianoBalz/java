package com.ficticiusclean.fleetsmanagement.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathOperationUtils {
	
	public static double roundTo(double vlr, int precision, RoundingMode roundMode) 
    {
        BigDecimal bd = new BigDecimal(vlr + "");
        BigDecimal rounded = bd.setScale(precision, roundMode);
        return rounded.doubleValue();
    }
	
	public static double multiplDouble(Double vlr1, Double vlr2, int nDecimal,  RoundingMode roundMode) {
		BigDecimal a;
		BigDecimal b;
		a = new BigDecimal(vlr1 + "");
		b = new BigDecimal(vlr2 + "");
		return roundTo(a.multiply(b).doubleValue(), nDecimal, roundMode);
	}

	public static double divideDouble(Double vlr1, Double vlr2, int nDecimal, RoundingMode roundMode) {
		BigDecimal a;
		BigDecimal b;

		a = new BigDecimal(vlr1 + "");
		b = new BigDecimal(vlr2 + "");
		if (b.doubleValue() == 0.00) {
			return 0.0d;
		}
		return a.divide(b, nDecimal, roundMode).doubleValue();

	}
	
	public static double somaDouble(Double vlr1, Double vlr2, int nDecimal, RoundingMode roundMode) {
		BigDecimal a;
		BigDecimal b;

		a = new BigDecimal(roundTo(vlr1, nDecimal, roundMode) + "");
		b = new BigDecimal(roundTo(vlr2, nDecimal, roundMode) + "");

		return a.add(b).doubleValue();
	}

}
