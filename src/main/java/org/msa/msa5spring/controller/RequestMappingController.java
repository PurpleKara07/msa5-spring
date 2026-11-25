package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RequestMappingController {
    @GetMapping
    public String index() {
        return "유저 조회";
    }
    @PostMapping
    public String store(){
        return "유저 생성";
    }
    @PutMapping
    public String update(){
        return "유저 수정";
    }
    @DeleteMapping
    public String destroy(){
        return "유저 삭제";
    }
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
        return  String.format("%d : %s",queryRequestDTO.id(), queryRequestDTO.name());
    }
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return  String.format("segment =  %d : %s",queryRequestDTO.id(), queryRequestDTO.name());
    }
    // "/segment/{id}/{name}" / "/segment/1/my" : 같은 패스
    // @GetMapping("/segment/1/my")
    // public String test(){
    //     return  "Test";
    // }
    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file){
        return String.format("%s : %s", file.getContentType(), file.getOriginalFilename());
    }
    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return  String.format("json =  %d : %s",queryRequestDTO.id(), queryRequestDTO.name());
    }

    // Query Parameter
    // -어노테이션 X, DTO객체
    // Segment Parameter
    // @PathVariable, 각 키에 맞는 파라미터를 별도로 컨트롤러 맵핑 파라미터로 설정
    // @ModelAttribute,  DTO객체 생성
    // Form Data
    // @ModelAttribute,  DTO객체 또는 컨트롤러 맵핑 파라미터로 설정
    // JSON방식
    // @RequestBody, DTO객체
}
