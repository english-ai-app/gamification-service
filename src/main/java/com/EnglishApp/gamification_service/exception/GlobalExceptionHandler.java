package com.EnglishApp.gamification_service.exception;

import com.EnglishApp.gamification_service.domain.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        Class<?> currentType = converterType;
        while (currentType != null) {
            String typeName = currentType.getName();
            if (typeName.equals(JacksonJsonHttpMessageConverter.class.getName())
                    || typeName.equals("org.springframework.http.converter.json.MappingJackson2HttpMessageConverter")) {
                return true;
            }
            currentType = currentType.getSuperclass();
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (!request.getURI().getPath().startsWith("/api/gamification/")
                || body == null
                || body instanceof ApiResponse<?>) {
            return body;
        }
        return new ApiResponse<>("SUCCESS", "Success", body);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException exception,
            HttpServletRequest request
    ) {
        log.warn(
                "Business request rejected method={} path={} status={} code={} reason={}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getStatus().value(),
                exception.getCode(),
                exception.getMessage()
        );
        return response(exception.getStatus(), exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Void>> handleResponseStatusException(
            ResponseStatusException exception,
            HttpServletRequest request
    ) {
        HttpStatusCode status = exception.getStatusCode();
        String message = exception.getReason() == null
                ? String.valueOf(status.value())
                : exception.getReason();
        log.warn(
                "Request rejected method={} path={} status={} reason={}",
                request.getMethod(),
                request.getRequestURI(),
                status.value(),
                message
        );
        return response(status, statusCode(status.value()), message);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ApiResponse<Void>> handleFrameworkError(
            ErrorResponseException exception,
            HttpServletRequest request
    ) {
        HttpStatusCode status = exception.getStatusCode();
        String message = exception.getBody().getDetail() == null
                ? "Request could not be processed"
                : exception.getBody().getDetail();
        log.warn(
                "Framework request error method={} path={} status={} reason={}",
                request.getMethod(),
                request.getRequestURI(),
                status.value(),
                message
        );
        return response(status, statusCode(status.value()), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .orElse(null);
        String message = fieldError == null
                ? "Request validation failed"
                : fieldError.getField() + ": " + fieldError.getDefaultMessage();
        log.warn(
                "Request validation failed method={} path={} reason={}",
                request.getMethod(),
                request.getRequestURI(),
                message
        );
        return response(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnreadableRequest(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        log.warn(
                "Request body could not be read method={} path={} reason={}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMostSpecificCause().getMessage()
        );
        return response(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST_BODY",
                "Request body is missing or invalid"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request
    ) {
        log.error(
                "Unhandled API error method={} path={}",
                request.getMethod(),
                request.getRequestURI(),
                exception
        );
        return response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "An unexpected server error occurred"
        );
    }

    private ResponseEntity<ApiResponse<Void>> response(
            HttpStatusCode status,
            String code,
            String message
    ) {
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(code, message, null));
    }

    private String statusCode(int status) {
        return switch (status) {
            case 400 -> "BAD_REQUEST";
            case 401 -> "UNAUTHORIZED";
            case 403 -> "FORBIDDEN";
            case 404 -> "NOT_FOUND";
            case 409 -> "CONFLICT";
            case 422 -> "UNPROCESSABLE_ENTITY";
            default -> status >= 400 && status < 500 ? "CLIENT_ERROR" : "SERVER_ERROR";
        };
    }
}
