package com.openclassrooms.starterjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String lastName;

	@NotBlank
	@Size(max = 20)
	private String firstName;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
