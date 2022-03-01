package com.company.M2ChallengeLimBlaze.controller;

import com.company.M2ChallengeLimBlaze.exceptions.NotFoundException;
import com.company.M2ChallengeLimBlaze.models.MathSolution;
import com.company.M2ChallengeLimBlaze.models.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class MonthAndMathServiceController {

    @GetMapping("/month/{monthNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Month monthConverter(@PathVariable int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Input out of range");
        }
        Month month = new Month(monthNumber);
        return month;
    }

    @GetMapping("/randomMonth")
    @ResponseStatus(value = HttpStatus.OK)
    public Month randomMonth() {
        int randomInt = ThreadLocalRandom.current().nextInt(1, 13);
        return new Month(randomInt);
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public MathSolution add(@RequestBody MathSolution mathSolution) {
        try {
            mathSolution.setOperation("add");
            mathSolution.setAnswer(mathSolution.getOperand1() + mathSolution.getOperand2());
            return mathSolution;
        } catch (Exception e) {
            throw new IllegalArgumentException("You must supply an integer value for both operand1 and operand 2");
        }
    }

    @PostMapping("/subtract")
    @ResponseStatus(value = HttpStatus.OK)
    public MathSolution subtract(@RequestBody MathSolution mathSolution) {
        try {
            mathSolution.setOperation("subtract");
            mathSolution.setAnswer(mathSolution.getOperand1() - mathSolution.getOperand2());
            return mathSolution;
        } catch (Exception e) {
            throw new IllegalArgumentException("You must supply an integer value for both operand1 and operand 2");
        }
    }

    @PostMapping("/multiply")
    @ResponseStatus(value = HttpStatus.OK)
    public MathSolution multiply(@RequestBody MathSolution mathSolution) {
        try {
            mathSolution.setOperation("multiply");
            mathSolution.setAnswer(mathSolution.getOperand1() * mathSolution.getOperand2());
            return mathSolution;
        } catch (Exception e) {
            throw new IllegalArgumentException("You must supply an integer value for both operand1 and operand 2");
        }
    }

    @PostMapping("/divide")
    @ResponseStatus(value = HttpStatus.OK)
    public MathSolution divide(@RequestBody MathSolution mathSolution) {
        String message = "You must supply an integer value for both operand1 and operand 2";

        try {
            if(mathSolution.getOperand2() == 0) {
                message = "operand2 cannot be zero";
            }
            mathSolution.setOperation("divide");
            mathSolution.setAnswer(mathSolution.getOperand1() / mathSolution.getOperand2());
            return mathSolution;
        } catch (Exception e) {
            throw new IllegalArgumentException(message);
        }
    }
}
