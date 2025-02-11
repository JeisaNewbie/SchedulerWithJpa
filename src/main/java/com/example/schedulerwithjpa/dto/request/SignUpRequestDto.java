package com.example.schedulerwithjpa.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;


@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식을 지켜주세요.")
    private final String email;

    @NotBlank
    @Size(min = 1, max = 10, message = "이름은 최소 1자리 최대 10자리 입니다.")
    private final String username;

    @NotNull(message = "비밀번호를 적어주세요.")
    @Range(min = 999, max = 9999999999L, message = "비밀번호는 최소 1자리 최대 10자리 입니다.")
    private final Long password;
}
