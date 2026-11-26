package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 유효성 체크 예외 관련
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GlobalResponseDTO<>(
                        "E01",
                        "유효성 체크 에러",
                        String.format("%s : 필드를 확인해주세요", e.getName())
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
                // getFieldErrors() : 필드에러, 리턴값 List /  key와 value로 사용 collect메서드 호출
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField, // 필드명을 가져와서 Map의 Key로 사용
                        (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다.",
                        // 기존값 / 새로운값 -> 기존값
                        (existing, replacement) -> existing // 키가 중복됐을때 처리
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new GlobalResponseDTO<>(
                    "E01",
                    "유효성 체크 에러",
                    errors
            )
        );
    }

    // 그외 모든 예외처리 핸들러 메서드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e) {
        return ResponseEntity.status(500).body(
            new GlobalResponseDTO<>(
                    "E99",
                    "시스템 에러",
                    null
            )
        );
    }
}
