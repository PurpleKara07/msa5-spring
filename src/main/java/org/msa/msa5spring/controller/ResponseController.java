package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseController {
    @GetMapping("/res")
    public ResponseEntity<GlobalResponseDTO<String>> index(){
        // return ResponseEntity.status(200).body("123");
        // return ResponseEntity.ok("123");
        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>("00", "정상처리", "레스폰스 테스트");
        return ResponseEntity.status(200).body(globalResponseDTO);
    }
    @GetMapping("res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test(){          // ResponseEntity = 1 / status = 2
        return ResponseEntity.status(200).body(     // body실행전에 new키워드가 먼저 실행 5
                new GlobalResponseDTO<>(            // 4
                    "00",
                    "정상처리",
                    new QueryRequestDTO(3L, "홍길동")  // 3
                )
        );
    }
}
