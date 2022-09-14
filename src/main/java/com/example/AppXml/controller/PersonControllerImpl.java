package com.example.AppXml.controller;

import com.example.AppXml.dto.PersonDto;
import com.example.AppXml.repo.Person;
import com.example.AppXml.service.JsonMapper;
import com.example.AppXml.service.PersonServiceImpl;
import com.example.AppXml.service.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController<Person,Integer> {

    public final PersonServiceImpl personServiceImpl;
    public final JsonMapper jsonMapper;
    public final XmlMapper xmlMapper;
    //wcześniej była klasa PersonMapper która
    // odnosiła się tylko do mapowania xmli, potem stworyliśmy interfejs i dwie klasy które obsługują
    // xml/json więc do której klasy mamy się odwołać w metodzie create jak nie wiadomo co to za format??


//    @PostMapping
//    public ResponseEntity<Person> createPerson(@RequestParam String path) throws IOException { //TODO: how the exceptions are handled, what about using aspects? PSB
//        personMapper.parser(path);
//        return ResponseEntity.ok().build();
//    }

    @Override
    public ResponseEntity<Person> createPerson(String path) throws IOException {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        personServiceImpl.getAll();
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<Person> getPersonById(@RequestParam Integer id) { //TODO: IMO path param will be better :)
        personServiceImpl.getById(id);
        return ResponseEntity.ok().build();
    }
}

/* ============================== Custom Global Exception Handler =================================================== */

//aspects.logTemplate.customGlobalExceptionHandler = [{0}] {1} Exception raised:
//aspects.logTemplate.autoLoggableAspect           = Data: {0} at {1}.{2}

/*
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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

import com.jfs.operations.lite.config.SystemProperties; // The class that contains ResourceBundle object with translations, DO NOT USE
import com.jfs.operations.lite.service.account.dto.JfsExceptionDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	// error handler for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
	                                                              HttpHeaders headers,
	                                                              HttpStatus status, WebRequest request) {
		log.error(getEndpointData(request) + e.getLocalizedMessage());
		Map<String, Object> body = prepareResponseMap(e, status);
		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception e) {
		log.error(getEndpointData(request.getMethod(), request.getRequestURL().toString()) + e.getLocalizedMessage());
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("500");
		return mav;
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
		return MessageFormat.format(SystemProperties.getResourceBundle().getString("aspects.logTemplate.customGlobalExceptionHandler"),
				((ServletWebRequest) request).getHttpMethod().toString(), request.getDescription(false));
	}
	// From Resource Bundle:
    // aspects.logTemplate.customGlobalExceptionHandler = [{0}] {1} Exception raised:
    // in your case should be as following:
    // MessageFormat.format("[{0}] {1} Exception raised: ", httpMethod, description)
	private String getEndpointData(String httpMethod, String description) {
		return MessageFormat.format(SystemProperties.getResourceBundle().getString("aspects.logTemplate.customGlobalExceptionHandler"),
				httpMethod, description);
	}

	private Map prepareResponseMap(Exception e, HttpStatus status) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		//Get all errors
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
 */

/* ============================== JfsExceptionDto - Custom Exception Dto ============================================ */

/*
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class JfsExceptionDto {
	public Date timestamp;
	public int status;
	public List<String> messages;
	public String path;
	public String method;
}
 */