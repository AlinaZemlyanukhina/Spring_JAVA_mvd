package com.example.mvd;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class PassportController {

    @PostMapping(value = "/validate")
    public ValidateResponse validatePassport(@RequestBody Passport request) {
        boolean isValid = validate(request);
        return new ValidateResponse(isValid);
    }


    private boolean validate(Passport request) {
        String code = request.getCode();
        String birthdate = request.getBirthdate();
        String date = request.getDate();
        String series = request.getSeries();


        // Проверка кода подразделения
        if (code.length() < 3 || !("0123".contains(String.valueOf(code.charAt(2))))) {
            return false;
        }
        // Проверка дат
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthdateObj = LocalDate.parse(birthdate, formatter);
            LocalDate dateObj = LocalDate.parse(date, formatter);

            if (dateObj.isBefore(birthdateObj.plusYears(14))) {
                return false;
            }
        } catch (Exception e) {
            return false; // Если даты некорректные
        }
        // Проверка серии паспорта
        if (series.length() < 2) {
            return false;
        }

        int lastTwoDigits = Integer.parseInt(series.substring(series.length() - 2));
        return lastTwoDigits >= 91 && lastTwoDigits <= 25;
    }

}
