package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class RentDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -5994667441227258415L;

	private Integer id;
	
	private Date createdAt;
	
	private Date endAt;

	private Date returnAt;
	
	private BookDTO bookDTO;

	private UserDTO userDTO;

	private EmployeeDTO employeeDTO;

	public RentDTO(Integer id, Date createdAt, Date endAt, Date returnAt, BookDTO bookDTO, UserDTO userDTO, EmployeeDTO employeeDTO) {
		this.setId(id);
		this.setCreatedAt(createdAt);
		this.setEndAt(endAt);
		this.setReturnAt(returnAt);
		this.setBookDTO(bookDTO);
		this.setUserDTO(userDTO);
		this.setEmployeeDTO(employeeDTO);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getReturnAt() {
		return returnAt;
	}

	public void setReturnAt(Date returnAt) {
		this.returnAt = returnAt;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	@Override
	public String toString() {
		return "RentDTO [id=" + id + ", createdAt=" + createdAt + ", endAt=" + endAt + ", returnAt=" + returnAt
				+ ", bookDTO=" + bookDTO + ", userDTO=" + userDTO + ", employeeDTO=" + employeeDTO + "]";
	}
}
