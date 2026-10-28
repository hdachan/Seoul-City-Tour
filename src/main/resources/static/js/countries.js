// src/main/resources/static/js/countries.js
// [한글명(DB 저장값), 영문명, 국가번호, 현지어(자국어) 이름]
// 드롭박스는 "현지어 (한글)" 형태로 표시됩니다. DB에는 한글명이 그대로 저장됩니다.
// ※ DMZ 평화관광(dmz.paju.go.kr) 출입신청서 국가 선택박스와 동일한 목록 (대한민국 포함 98개)
//   엑셀 업로드 시 국가번호가 이 목록에 없으면 업로드가 거부되므로, 추가/수정 시 DMZ 목록과 맞춰야 합니다.
const COUNTRIES = [
    ["대한민국", "Korea, Republic of", "82", "대한민국"],
    ["미국", "United States", "1", "United States"],
    ["일본", "Japan", "81", "日本"],
    ["중국", "China", "86", "中国"],
    ["영국", "United Kingdom", "44", "United Kingdom"],
    ["독일", "Germany", "49", "Deutschland"],
    ["이탈리아", "Italy", "39", "Italia"],
    ["프랑스", "France", "33", "France"],
    ["가나", "Ghana", "233", "Ghana"],
    ["괌", "Guam", "1", "Guam"],
    ["그리스", "Greece", "30", "Ελλάδα"],
    ["나이지리아", "Nigeria", "234", "Nigeria"],
    ["남아프리카 공화국", "South Africa", "27", "South Africa"],
    ["네델란드", "Netherlands", "31", "Nederland"],
    ["노르웨이", "Norway", "47", "Norge"],
    ["뉴질랜드", "New Zealand", "64", "New Zealand"],
    ["대만", "Taiwan, Province of China", "886", "臺灣"],
    ["덴마크", "Denmark", "45", "Danmark"],
    ["라오스", "Lao People's Democratic Republic", "856", "ລາວ"],
    ["러시아", "Russian Federation", "7", "Россия"],
    ["레바논", "Lebanon", "961", "لبنان"],
    ["루마니아", "Romania", "40", "România"],
    ["말레이시아", "Malaysia", "60", "Malaysia"],
    ["말리", "Mali", "223", "Mali"],
    ["멕시코", "Mexico", "52", "México"],
    ["모로코", "Morocco", "212", "المغرب"],
    ["몽골", "Mongolia", "976", "Монгол улс"],
    ["방글라데시", "Bangladesh", "880", "বাংলাদেশ"],
    ["베트남", "Viet Nam", "84", "Việt Nam"],
    ["벨기에", "Belgium", "32", "België"],
    ["불가리아", "Bulgaria", "359", "България"],
    ["브라질", "Brazil", "55", "Brasil"],
    ["사우디 아라비아", "Saudi Arabia", "966", "السعودية"],
    ["세네갈", "Senegal", "221", "Sénégal"],
    ["수단", "Sudan", "249", "السودان"],
    ["스웨덴", "Sweden", "46", "Sverige"],
    ["스위스", "Switzerland", "41", "Schweiz"],
    ["스페인", "Spain", "34", "España"],
    ["슬로바키아", "Slovakia", "421", "Slovensko"],
    ["슬로베니아", "Slovenia", "386", "Slovenija"],
    ["싱가포르", "Singapore", "65", "Singapore"],
    ["아랍 에리레이트", "United Arab Emirates", "971", "الإمارات"],
    ["아르메니아", "Armenia", "374", "Հայաստան"],
    ["아르헨티나", "Argentina", "54", "Argentina"],
    ["아이슬란드", "Iceland", "354", "Ísland"],
    ["아일랜드", "Ireland", "353", "Éire"],
    ["안도라", "Andorra", "376", "Andorra"],
    ["알제리아", "Algeria", "213", "الجزائر"],
    ["앙골라", "Angola", "244", "Angola"],
    ["에콰도르", "Ecuador", "593", "Ecuador"],
    ["에티오피아", "Ethiopia", "251", "ኢትዮጵያ"],
    ["엘살바도르", "El Salvador", "503", "El Salvador"],
    ["영국령 인도양", "British Indian Ocean Territory", "246", "British Indian Ocean Territory"],
    ["예멘", "Yemen", "967", "اليمن"],
    ["오만 이슬람", "Oman", "968", "عُمان"],
    ["오스트레일리아", "Australia", "61", "Australia"],
    ["오스트리아", "Austria", "43", "Österreich"],
    ["온두라스", "Honduras", "504", "Honduras"],
    ["요르단", "Jordan", "962", "الأردن"],
    ["우간다", "Uganda", "256", "Uganda"],
    ["우루과이", "Uruguay", "598", "Uruguay"],
    ["우즈베키스탄", "Uzbekistan", "998", "O‘zbekiston"],
    ["우크라이나", "Ukraine", "380", "Україна"],
    ["이라크", "Iraq", "964", "العراق"],
    ["이란", "Iran, Islamic Republic of", "98", "ایران"],
    ["이스라엘", "Israel", "972", "ישראל"],
    ["이집트", "Egypt", "20", "مصر"],
    ["인도", "India", "91", "भारत"],
    ["인도네시아", "Indonesia", "62", "Indonesia"],
    ["체코", "Czech Republic", "420", "Česko"],
    ["칠레", "Chile", "56", "Chile"],
    ["카메룬", "Cameroon", "237", "Cameroun"],
    ["카타르국", "Qatar", "974", "قطر"],
    ["캄보디아", "Cambodia", "855", "កម្ពុជា"],
    ["캐나다", "Canada", "1", "Canada"],
    ["케냐", "Kenya", "254", "Kenya"],
    ["코스타리카", "Costa Rica", "506", "Costa Rica"],
    ["콜롬비아", "Colombia", "57", "Colombia"],
    ["콩고", "Congo", "242", "Congo"],
    ["콩고 민주 공화국", "Congo, the Democratic Republic of the", "243", "République démocratique du Congo"],
    ["쿠바", "Cuba", "53", "Cuba"],
    ["쿠웨이트", "Kuwait", "965", "الكويت"],
    ["크로아티아", "Croatia", "385", "Hrvatska"],
    ["키르기스스탄", "Kyrgyzstan", "996", "Кыргызстан"],
    ["타이", "Thailand", "66", "ประเทศไทย"],
    ["탄자니아 공화국", "Tanzania, United Republic of", "255", "Tanzania"],
    ["터키", "Turkey", "90", "Türkiye"],
    ["파나마", "Panama", "507", "Panamá"],
    ["파라과이", "Paraguay", "595", "Paraguay"],
    ["파키스탄", "Pakistan", "92", "پاکستان"],
    ["페루", "Peru", "51", "Perú"],
    ["포르투갈", "Portugal", "351", "Portugal"],
    ["폴란드", "Poland", "48", "Polska"],
    ["피지", "Fiji", "679", "Viti"],
    ["핀란드", "Finland", "358", "Suomi"],
    ["필리핀", "Philippines", "63", "Pilipinas"],
    ["헝가리", "Hungary", "36", "Magyarország"],
    ["홍콩", "Hong Kong", "852", "香港"]
].map(([ko, en, code, native]) => ({ ko, en, code, native }));

// 상단 "Popular" 그룹에 보여줄 나라 (한글명 기준)
const POPULAR_COUNTRIES = ['영국', '싱가포르', '필리핀', '홍콩', '베트남', '캐나다', '미국', '일본', '중국', '대만', '타이', '대한민국'];

// 한글명 -> 국가 객체
function findCountry(ko) {
    return COUNTRIES.find(c => c.ko === ko) || null;
}

// 한글명 -> 국가번호 (없으면 빈 문자열)
function countryCode(ko) {
    const c = findCountry(ko);
    return c ? c.code : '';
}

// <select>에 넣을 <option> HTML
// lang: 'native' -> "France (프랑스)"  (기본값, 현지어 + 한글)
//       'en'     -> "United States (미국)"
//       'ko'     -> "미국 (United States)"
function buildCountryOptions(lang = 'native') {
    const label = c => {
        if (lang === 'ko') return `${c.ko} (${c.en})`;
        if (lang === 'en') return `${c.en} (${c.ko})`;
        return `${c.native} (${c.ko})`; // native
    };
    const opt = c => `<option value="${c.ko}">${label(c)}</option>`;
    const sorted = [...COUNTRIES].sort((a, b) => {
        if (lang === 'ko') return a.ko.localeCompare(b.ko, 'ko');
        if (lang === 'en') return a.en.localeCompare(b.en);
        return a.native.localeCompare(b.native);
    });
    const placeholder = lang === 'ko' ? '국가 선택' : 'Select country';
    return `
        <option value="" disabled selected>${placeholder}</option>
        <optgroup label="${lang === 'ko' ? '자주 선택' : 'Popular'}">
            ${POPULAR_COUNTRIES.map(k => opt(findCountry(k))).join('')}
        </optgroup>
        <optgroup label="${lang === 'ko' ? '전체' : 'All countries'}">
            ${sorted.map(opt).join('')}
        </optgroup>`;
}