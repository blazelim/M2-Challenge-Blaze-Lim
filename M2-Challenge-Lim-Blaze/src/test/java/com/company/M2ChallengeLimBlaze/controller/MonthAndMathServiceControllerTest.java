package com.company.M2ChallengeLimBlaze.controller;

import com.company.M2ChallengeLimBlaze.models.MathSolution;
import com.company.M2ChallengeLimBlaze.models.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthAndMathServiceController.class)
public class MonthAndMathServiceControllerTest {

    // Wire in the mockmvc object
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){

    }

    @Test
    public void shouldReturnCorrectMonth() throws Exception {
        Month outputMonth = new Month(5);
        String outputJson = mapper.writeValueAsString(outputMonth);

        mockMvc.perform(get("/month/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnIllegalArgumentExceptionForMonth13() throws Exception {
        mockMvc.perform(get("/month/13"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("Input out of range", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldReturnRandomMonth() throws Exception {

        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAdd2Numbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand1(42);
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        MathSolution outputSolution = new MathSolution(42, 6);
        outputSolution.setOperation("add");
        outputSolution.setAnswer(48);
        String outputJson = mapper.writeValueAsString(outputSolution);

        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson)
        );
    }

    @Test
    public void shouldNotAdd2NotNumbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("You must supply an integer value for both operand1 and operand 2", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldSubtract2Numbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand1(42);
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        MathSolution outputSolution = new MathSolution(42, 6);
        outputSolution.setOperation("subtract");
        outputSolution.setAnswer(36);
        String outputJson = mapper.writeValueAsString(outputSolution);

        mockMvc.perform(post("/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson)
                );
    }

    @Test
    public void shouldNotSubtract2NotNumbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        mockMvc.perform(post("/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("You must supply an integer value for both operand1 and operand 2", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldMultiply2Numbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand1(42);
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        MathSolution outputSolution = new MathSolution(42, 6);
        outputSolution.setOperation("multiply");
        outputSolution.setAnswer(252);
        String outputJson = mapper.writeValueAsString(outputSolution);

        mockMvc.perform(post("/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson)
                );
    }

    @Test
    public void shouldNotMultiply2NotNumbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        mockMvc.perform(post("/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("You must supply an integer value for both operand1 and operand 2", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldDivide2Numbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand1(42);
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        MathSolution outputSolution = new MathSolution(42, 6);
        outputSolution.setOperation("divide");
        outputSolution.setAnswer(7);
        String outputJson = mapper.writeValueAsString(outputSolution);

        mockMvc.perform(post("/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson)
                );
    }

    @Test
    public void shouldNotDivide2NotNumbers() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand2(6);
        String inputJson = mapper.writeValueAsString(inputSolution);

        mockMvc.perform(post("/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("You must supply an integer value for both operand1 and operand 2", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldNotDivideByZero() throws Exception {
        MathSolution inputSolution = new MathSolution();
        inputSolution.setOperand1(42);
        inputSolution.setOperand2(0);
        String inputJson = mapper.writeValueAsString(inputSolution);

        mockMvc.perform(post("/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals("operand2 cannot be zero", result.getResolvedException().getMessage()));
    }
}