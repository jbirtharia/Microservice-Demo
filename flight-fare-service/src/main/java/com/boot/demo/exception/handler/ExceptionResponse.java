package com.boot.demo.exception.handler;

import lombok.*;
import java.time.LocalDateTime;

/**
 * The type Exception response.
 *
 * @author JayendraB
 * @createdOn 7 /10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private LocalDateTime timeStamp;

    private int status;

    private String message;

    private  String path;
}
