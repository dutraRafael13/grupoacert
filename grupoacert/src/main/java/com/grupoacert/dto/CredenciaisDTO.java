package com.grupoacert.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredenciaisDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;

}
