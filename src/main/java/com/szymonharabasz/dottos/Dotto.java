package com.szymonharabasz.dottos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record Dotto(String title, String description, LocalDateTime created, @Min(0) @Max(10) int score) {
}
