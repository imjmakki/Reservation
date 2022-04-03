package io.reservation.rest;

import io.reservation.model.ReservatoiinDTO;
import io.reservation.service.ReservatoiinService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/reservatoiins", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservatoiinController {

    private final ReservatoiinService reservatoiinService;

    public ReservatoiinController(final ReservatoiinService reservatoiinService) {
        this.reservatoiinService = reservatoiinService;
    }

    @GetMapping
    public ResponseEntity<List<ReservatoiinDTO>> getAllReservatoiins() {
        return ResponseEntity.ok(reservatoiinService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservatoiinDTO> getReservatoiin(@PathVariable final Long id) {
        return ResponseEntity.ok(reservatoiinService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createReservatoiin(
            @RequestBody @Valid final ReservatoiinDTO reservatoiinDTO) {
        return new ResponseEntity<>(reservatoiinService.create(reservatoiinDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservatoiin(@PathVariable final Long id,
            @RequestBody @Valid final ReservatoiinDTO reservatoiinDTO) {
        reservatoiinService.update(id, reservatoiinDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservatoiin(@PathVariable final Long id) {
        reservatoiinService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
