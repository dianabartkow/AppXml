package com.example.AppXml;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private MethodArgumentNotValidException e;
    private HttpHeaders headers;
    private HttpStatus status;
    private WebRequest request;

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.e = e;
        this.headers = headers;
        this.status = status;
        this.request = request;
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        Map<String, Object> body = prepareResponseMap(e, status);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e)
    {
        logger.error("Request: " + request.getMethod() + " raised " + e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.BAD_REQUEST);
    }


    private String getEndpointData(WebRequest request) {
        return MessageFormat.format(SystemProperties.get("aspects.logTemplate.customGlobalExceptionHandler"),
                ((ServletWebRequest) request).getHttpMethod().toString(), request.getDescription(false));
    }

    private Map prepareResponseMap(Exception e, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = new ArrayList<>();
        if (e instanceof MethodArgumentNotValidException)
            errors = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        else
            errors.add(e.getMessage());
        body.put("errors", errors);
        return body;
    }

//java server Faces??
    private JfsExceptionDto prepareCustomExceptionDto(Exception e, HttpStatus status, List<String> messages, String path, String method) {
        return JfsExceptionDto.builder()
                .timestamp(new Date())
                .status(status.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();
    }
}
