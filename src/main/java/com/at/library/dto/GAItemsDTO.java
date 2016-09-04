package com.at.library.dto;

import java.io.Serializable;

public class GAItemsDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 3673443589402171206L;

	private GAVolumeInfoDTO volumeInfo;

	public GAVolumeInfoDTO getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(GAVolumeInfoDTO volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	
	@Override
	public String toString() {
		return "GAItemsDTO [volumeInfo=" + volumeInfo + "]";
	}
}
