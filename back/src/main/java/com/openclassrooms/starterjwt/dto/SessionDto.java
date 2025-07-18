package com.openclassrooms.starterjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotNull
	private Date date;

	@NotNull
	private Long teacher_id;

	@NotNull
	@Size(max = 2500)
	private String description;

	private List<Long> users;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
