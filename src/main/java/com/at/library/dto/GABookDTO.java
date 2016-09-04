package com.at.library.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GABookDTO extends DTO implements Serializable {
	
	private static final long serialVersionUID = -9088817072899657325L;
	
	private GAItemsDTO[] items;

	public GAItemsDTO[] getItems() {
		return items;
	}

	public void setItems(GAItemsDTO[] items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GABookDTO [items=" + Arrays.toString(items) + "]";
	}
}
