/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2020 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isf.shared.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.isf.utils.exception.OHServiceException;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

//import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Exception DTO
 *
 * @author antonio
 */
@Getter
@Setter
public class OHAPIError {
    private HttpStatus status;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String message;
    private String debugMessage;
    private String stackTrace;
    private LocalDateTime timestamp;

    public OHAPIError(HttpStatus status, OHServiceException ex) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = ex.getMessages().get(0).getMessage();
        this.debugMessage = ex.getMessages()
                .stream()
                .map(em -> em.getMessage())
                .collect(Collectors.joining(","));
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
    }

}
