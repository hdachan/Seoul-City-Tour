//// src/main/java/org/example/seoulcitytourdemo/controller/DataInitController.java
//package org.example.seoulcitytourdemo.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.seoulcitytourdemo.service.DATATouristService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/dev")
//@RequiredArgsConstructor
//public class DataInitController {
//
//    private final DATATouristService dataTouristService;
//
//    @GetMapping("/create")
//    public String create(
//            @RequestParam(defaultValue = "2000") int count,
//            @RequestParam(defaultValue = "2025-11-24") String date) {
//        dataTouristService.createDummyTourists(count, date);
//        return count + "명 생성 완료! (" + date + ")";
//    }
//}