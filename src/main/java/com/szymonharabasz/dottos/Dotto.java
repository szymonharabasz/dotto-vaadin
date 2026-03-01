package com.szymonharabasz.dottos;

import java.time.LocalDateTime;

public record Dotto(String title, String description, LocalDateTime created, int rating) {
}
