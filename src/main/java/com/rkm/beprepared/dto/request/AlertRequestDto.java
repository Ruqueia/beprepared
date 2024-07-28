package com.rkm.beprepared.dto.request;

import com.rkm.beprepared.model.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private Severity severity;
    @NotNull
    private Long provinceId;
    @NotNull
    private Long cityId;

}
