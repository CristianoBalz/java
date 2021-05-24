package br.com.texoit.worstmovies.model;

import java.util.stream.Stream;

public enum WinnerEnum {
	YES("yes"),
	NO("");
	
	private String value;
	
	WinnerEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
	
	public static WinnerEnum of(String value) {
		return Stream.of(WinnerEnum.values())
        .filter(p -> p.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    } 
}
