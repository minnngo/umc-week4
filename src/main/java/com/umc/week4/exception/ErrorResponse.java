package com.umc.week4.exception;

public record ErrorResponse(
        int status,
        String message
) {}
