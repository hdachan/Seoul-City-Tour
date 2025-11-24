//package org.example.seoulcitytourdemo.service;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.example.seoulcitytourdemo.entity.Guide;
//import org.example.seoulcitytourdemo.entity.Tourist;
//import org.example.seoulcitytourdemo.repository.TouristRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class DATATouristService {
//
//    private final TouristRepository touristRepository;
//
//    private static final UUID GUIDE_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
//
//    private static final String[] LAST_NAMES = {"김","이","박","정","최","조","윤","장","임","한","오","서","신","권","황"};
//    private static final String[] MALE_FIRST = {"민준","서준","도윤","시우","하준","지호","준우","예준","현우","지훈"};
//    private static final String[] FEMALE_FIRST = {"서연","하윤","지안","서윤","지우","하은","지윤","수아","시아","예은"};
//    private static final String[] COUNTRIES = {"대한민국","중국","일본","미국","대만","베트남","태국","몽골","필리핀","홍콩"};
//
//    public void createDummyTourists(int count, String date) {
//        List<Tourist> list = new ArrayList<>();
//        Random r = new Random();
//
//        for (int i = 0; i < count; i++) {
//            String gender = r.nextBoolean() ? "MALE" : "FEMALE";
//            String name = LAST_NAMES[r.nextInt(LAST_NAMES.length)] +
//                    (gender.equals("MALE") ? MALE_FIRST[r.nextInt(MALE_FIRST.length)] : FEMALE_FIRST[r.nextInt(FEMALE_FIRST.length)]);
//
//            Tourist t = Tourist.builder()
//                    .id(UUID.randomUUID())  // 우리가 직접 넣음 → 문제 없음!
//                    .guide(Guide.builder().id(GUIDE_ID).build())
//                    .name(name)
//                    .birth(LocalDate.of(1960 + r.nextInt(46), 1 + r.nextInt(12), 1 + r.nextInt(28)))
//                    .gender(gender)
//                    .country(COUNTRIES[r.nextInt(COUNTRIES.length)])
//                    .phone("010" + String.format("%08d", r.nextInt(100000000)))
//                    .time(LocalDateTime.parse(date + "T" + String.format("%02d:%02d:00",
//                            8 + r.nextInt(11), r.nextInt(60))))
//                    .build();
//
//            list.add(t);
//
//            if (list.size() == 500) {
//                touristRepository.saveAll(list);  // saveAll만 써야 안전!
//                list.clear();
//            }
//        }
//
//        if (!list.isEmpty()) {
//            touristRepository.saveAll(list);
//        }
//
//        System.out.println("더미 데이터 " + count + "명 생성 완료! (" + date + ")");
//    }
//}