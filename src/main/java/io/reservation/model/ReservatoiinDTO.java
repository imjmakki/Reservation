package io.reservation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservatoiinDTO {

    private Long id;

    @Size(max = 255)
    private String reservationDate;

    @Schema(type = "string", example = "14:30")
    private LocalTime startTime;

    @Schema(type = "string", example = "14:30")
    private LocalTime endTime;

    @NotNull
    private Long user;

}
