package io.reservation.service;

import io.reservation.domain.Reservatoiin;
import io.reservation.domain.User;
import io.reservation.model.ReservatoiinDTO;
import io.reservation.repos.ReservatoiinRepository;
import io.reservation.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservatoiinService {

    private final ReservatoiinRepository reservatoiinRepository;
    private final UserRepository userRepository;

    public ReservatoiinService(final ReservatoiinRepository reservatoiinRepository,
            final UserRepository userRepository) {
        this.reservatoiinRepository = reservatoiinRepository;
        this.userRepository = userRepository;
    }

    public List<ReservatoiinDTO> findAll() {
        return reservatoiinRepository.findAll()
                .stream()
                .map(reservatoiin -> mapToDTO(reservatoiin, new ReservatoiinDTO()))
                .collect(Collectors.toList());
    }

    public ReservatoiinDTO get(final Long id) {
        return reservatoiinRepository.findById(id)
                .map(reservatoiin -> mapToDTO(reservatoiin, new ReservatoiinDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ReservatoiinDTO reservatoiinDTO) {
        final Reservatoiin reservatoiin = new Reservatoiin();
        mapToEntity(reservatoiinDTO, reservatoiin);
        return reservatoiinRepository.save(reservatoiin).getId();
    }

    public void update(final Long id, final ReservatoiinDTO reservatoiinDTO) {
        final Reservatoiin reservatoiin = reservatoiinRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(reservatoiinDTO, reservatoiin);
        reservatoiinRepository.save(reservatoiin);
    }

    public void delete(final Long id) {
        reservatoiinRepository.deleteById(id);
    }

    private ReservatoiinDTO mapToDTO(final Reservatoiin reservatoiin,
            final ReservatoiinDTO reservatoiinDTO) {
        reservatoiinDTO.setId(reservatoiin.getId());
        reservatoiinDTO.setReservationDate(reservatoiin.getReservationDate());
        reservatoiinDTO.setStartTime(reservatoiin.getStartTime());
        reservatoiinDTO.setEndTime(reservatoiin.getEndTime());
        reservatoiinDTO.setUser(reservatoiin.getUser() == null ? null : reservatoiin.getUser().getId());
        return reservatoiinDTO;
    }

    private Reservatoiin mapToEntity(final ReservatoiinDTO reservatoiinDTO,
            final Reservatoiin reservatoiin) {
        reservatoiin.setReservationDate(reservatoiinDTO.getReservationDate());
        reservatoiin.setStartTime(reservatoiinDTO.getStartTime());
        reservatoiin.setEndTime(reservatoiinDTO.getEndTime());
        if (reservatoiinDTO.getUser() != null && (reservatoiin.getUser() == null || !reservatoiin.getUser().getId().equals(reservatoiinDTO.getUser()))) {
            final User user = userRepository.findById(reservatoiinDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            reservatoiin.setUser(user);
        }
        return reservatoiin;
    }

}
