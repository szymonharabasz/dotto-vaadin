package com.szymonharabasz.dottos;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DottosService {
    List<Dotto> dottos = List.of(
            new Dotto(
                    "Started backend project",
                    "Started a backed project for Dottos in Quarkus",
                    LocalDateTime.now().minusMonths(3),
                    4
                    ),
            new Dotto(
                    "Started frontend project",
                    "Started a frontend project for Dottos in Vaadin",
                    LocalDateTime.now(),
                    3
            ),
            new Dotto(
                    "Self-consistency test passed for ideal momenta",
                    "Achieved a state when the self-consistency check is passed for ideal momenta if the events are selected with opening angle of embedded pair ro be larger than 9 degree.",
                    LocalDateTime.now(),
                    4
            )
    );
    public List<Dotto> getDottos() {
        return dottos;
    }
}
