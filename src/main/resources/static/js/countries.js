// src/main/resources/static/js/countries.js
// [한글명(DB 저장값), 영문명, 휴대폰 국가번호, 현지어(자국어) 이름]
// 드롭박스는 "현지어 (한글)" 형태로 표시됩니다. DB에는 한글명이 그대로 저장됩니다.
// ※ DMZ 평화관광(dmz.paju.go.kr) 출입신청서 '국가/지역 선택' 목록과 동일 (대한민국 포함 239개)
//   엑셀 '국가/지역' 칸에 한글명이 그대로 들어가므로, 추가/수정 시 DMZ 목록의 표기와 똑같이 맞춰야 합니다.
//   국가번호는 DMZ에서 지원하는 나라만 있고(98개), 빈 나라는 엑셀에 82로 들어갑니다.
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
    ["가봉", "Gabon", "", "Gabon"],
    ["가이아나", "Guyana", "", "Guyana"],
    ["감비아", "Gambia", "", "Gambia"],
    ["과둘루프", "Guadeloupe", "", "Guadeloupe"],
    ["과테말라", "Guatemala", "", "Guatemala"],
    ["괌", "Guam", "1", "Guam"],
    ["그레나다", "Grenada", "", "Grenada"],
    ["그리스", "Greece", "30", "Ελλάδα"],
    ["그린란드", "Greenland", "", "Greenland"],
    ["기니", "Guinea", "", "Guinea"],
    ["기니-비사우", "Guinea-Bissau", "", "Guinea-Bissau"],
    ["나미비아", "Namibia", "", "Namibia"],
    ["나우루", "Nauru", "", "Nauru"],
    ["나이지리아", "Nigeria", "234", "Nigeria"],
    ["남극 대륙", "Antarctica", "", "Antarctica"],
    ["남아프리카 공화국", "South Africa", "27", "South Africa"],
    ["네덜란드령 앤틸리스 제도", "Antilles", "", "Antilles"],
    ["네델란드", "Netherlands", "31", "Nederland"],
    ["네팔", "Nepal", "", "Nepal"],
    ["노르웨이", "Norway", "47", "Norge"],
    ["노퍽 섬", "Norfolk Island", "", "Norfolk Island"],
    ["뉴 칼레도니아 섬", "New Caledonia", "", "New Caledonia"],
    ["뉴질랜드", "New Zealand", "64", "New Zealand"],
    ["니우 섬", "Niue", "", "Niue"],
    ["니제르", "Niger", "", "Niger"],
    ["니카라과이", "Nicaragua", "", "Nicaragua"],
    ["대만", "Taiwan, Province of China", "886", "臺灣"],
    ["덴마크", "Denmark", "45", "Danmark"],
    ["도미니카", "Dominica", "", "Dominica"],
    ["도미니카 공화국", "Dominican Republic", "", "Dominican Republic"],
    ["동 티모르", "East Timor", "", "East Timor"],
    ["라오스", "Lao People's Democratic Republic", "856", "ລາວ"],
    ["라이베리아", "Liberia", "", "Liberia"],
    ["라트비아", "Latvia", "", "Latvia"],
    ["러시아", "Russian Federation", "7", "Россия"],
    ["레바논", "Lebanon", "961", "لبنان"],
    ["레소토", "Lesotho", "", "Lesotho"],
    ["루마니아", "Romania", "40", "România"],
    ["룩셈부르크", "Luxembourg", "", "Luxembourg"],
    ["르완다", "Rwanda", "", "Rwanda"],
    ["리비아", "Libya", "", "Libya"],
    ["리위니옹", "Reunion", "", "Reunion"],
    ["리투아니아", "Lithuania", "", "Lithuania"],
    ["리히텐슈테인", "Liechtenstein", "", "Liechtenstein"],
    ["마다가스카르", "Madagascar", "", "Madagascar"],
    ["마르티니크 섬", "Martinique", "", "Martinique"],
    ["마샬 제도", "Marshall Islands", "", "Marshall Islands"],
    ["마요트", "Mayotte", "", "Mayotte"],
    ["마이너 아웃라일 제도", "United States Minor Outlying Islands", "", "United States Minor Outlying Islands"],
    ["마카오", "Macao", "", "Macao"],
    ["마케도니아", "Macedonia, The Former Yugoslav Republic of", "", "Macedonia, The Former Yugoslav Republic of"],
    ["말라위", "Malawi", "", "Malawi"],
    ["말레이시아", "Malaysia", "60", "Malaysia"],
    ["말리", "Mali", "223", "Mali"],
    ["멕시코", "Mexico", "52", "México"],
    ["모나코", "Monaco", "", "Monaco"],
    ["모로코", "Morocco", "212", "المغرب"],
    ["모리셔스", "Mauritius", "", "Mauritius"],
    ["모리타니아", "Mauritania", "", "Mauritania"],
    ["모잠비크", "Mozambique", "", "Mozambique"],
    ["몬트세라트 섬", "Montserrat", "", "Montserrat"],
    ["몰도바 공화국", "Moldova, Republic of", "", "Moldova, Republic of"],
    ["몰디브", "Maldives", "", "Maldives"],
    ["몰타", "Malta", "", "Malta"],
    ["몽골", "Mongolia", "976", "Монгол улс"],
    ["미얀마", "Myanmar", "", "Myanmar"],
    ["미연방 버진 군도", "Virgin Islands, U.S.", "", "Virgin Islands, U.S."],
    ["미크로네시아 연합", "Micronesia, Federated States of", "", "Micronesia, Federated States of"],
    ["바누아투", "Vanuatu", "", "Vanuatu"],
    ["바레인국", "Bahrain", "", "Bahrain"],
    ["바베이도스", "Barbados", "", "Barbados"],
    ["바티칸 시국", "Holy See (Vatican City State)", "", "Holy See (Vatican City State)"],
    ["바하마", "Bahamas", "", "Bahamas"],
    ["방글라데시", "Bangladesh", "880", "বাংলাদেশ"],
    ["버뮤다 제도", "Bermuda", "", "Bermuda"],
    ["베냉", "Benin", "", "Benin"],
    ["베네수엘라", "Venezuela, Bolivarian Republic of", "", "Venezuela, Bolivarian Republic of"],
    ["베트남", "Viet Nam", "84", "Việt Nam"],
    ["벨기에", "Belgium", "32", "België"],
    ["벨로루시", "Belarus", "", "Belarus"],
    ["벨리제", "Belize", "", "Belize"],
    ["보스니아 헤르체고비나", "Bosnia and Herzegovina", "", "Bosnia and Herzegovina"],
    ["보츠와나", "Botswana", "", "Botswana"],
    ["볼리비아", "Bolivia, Plurinational State of", "", "Bolivia, Plurinational State of"],
    ["부룬디 공화국", "Burundi", "", "Burundi"],
    ["부르키나 파소", "Burkina Faso", "", "Burkina Faso"],
    ["부베섬", "Bouvet Island", "", "Bouvet Island"],
    ["부탄", "Bhutan", "", "Bhutan"],
    ["북 마리아나 제도", "Northern Mariana Islands", "", "Northern Mariana Islands"],
    ["북한", "Korea, Democratic People's Republic of", "", "Korea, Democratic People's Republic of"],
    ["불가리아", "Bulgaria", "359", "България"],
    ["브라질", "Brazil", "55", "Brasil"],
    ["브루나이 술탄", "Brunei Darussalam", "", "Brunei Darussalam"],
    ["사모아", "Samoa", "", "Samoa"],
    ["사우디 아라비아", "Saudi Arabia", "966", "السعودية"],
    ["사우스 조지아 앤드 더 사우스 샌드위치 제도", "South Georgia and the South Sandwich Islands", "", "South Georgia and the South Sandwich Islands"],
    ["산 마리노", "San Marino", "", "San Marino"],
    ["상 투메 앤드 프린시페", "Sao Tome and Principe", "", "Sao Tome and Principe"],
    ["생피에르 앤드 미클롱", "Saint Pierre and Miquelon", "", "Saint Pierre and Miquelon"],
    ["세네갈", "Senegal", "221", "Sénégal"],
    ["세르비아", "Serbia", "", "Serbia"],
    ["세이셀", "Seychelles", "", "Seychelles"],
    ["세인트 루시아", "Saint Lucia", "", "Saint Lucia"],
    ["세인트 헬레나 섬", "Saint Helena, Ascension and Tristan da Cunha", "", "Saint Helena, Ascension and Tristan da Cunha"],
    ["세인트빈센트그레나딘", "Saint Vincent and the Grenadines", "", "Saint Vincent and the Grenadines"],
    ["세인트키츠네비스", "Saint Kitts and Nevis", "", "Saint Kitts and Nevis"],
    ["소말리아", "Somalia", "", "Somalia"],
    ["솔로몬 제도", "Solomon Islands", "", "Solomon Islands"],
    ["수단", "Sudan", "249", "السودان"],
    ["수리남", "Suriname", "", "Suriname"],
    ["스리랑카", "Sri Lanka", "", "Sri Lanka"],
    ["스발바드 잔 메이언", "Svalbard and Jan Mayen", "", "Svalbard and Jan Mayen"],
    ["스와질랜드", "Swaziland", "", "Swaziland"],
    ["스웨덴", "Sweden", "46", "Sverige"],
    ["스위스", "Switzerland", "41", "Schweiz"],
    ["스페인", "Spain", "34", "España"],
    ["슬로바키아", "Slovakia", "421", "Slovensko"],
    ["슬로베니아", "Slovenia", "386", "Slovenija"],
    ["시리안 아랍", "Syrian Arab Republic", "", "Syrian Arab Republic"],
    ["시에라리온", "Sierra Leone", "", "Sierra Leone"],
    ["싱가포르", "Singapore", "65", "Singapore"],
    ["아랍 에리레이트", "United Arab Emirates", "971", "الإمارات"],
    ["아루바", "Aruba", "", "Aruba"],
    ["아르메니아", "Armenia", "374", "Հայաստան"],
    ["아르헨티나", "Argentina", "54", "Argentina"],
    ["아메리칸 사모아", "American Samoa", "", "American Samoa"],
    ["아이슬란드", "Iceland", "354", "Ísland"],
    ["아이티", "Haiti", "", "Haiti"],
    ["아일랜드", "Ireland", "353", "Éire"],
    ["아제르바이젠 공화국", "Azerbaijan", "", "Azerbaijan"],
    ["아프가니스탄", "Afghanistan", "", "Afghanistan"],
    ["안도라", "Andorra", "376", "Andorra"],
    ["안티가 앤드 바부다 공화국", "Antigua and Barbuda", "", "Antigua and Barbuda"],
    ["알바니아", "Albania", "", "Albania"],
    ["알제리아", "Algeria", "213", "الجزائر"],
    ["앙골라", "Angola", "244", "Angola"],
    ["앨귈라", "Anguilla", "", "Anguilla"],
    ["에리트레아", "Eritrea", "", "Eritrea"],
    ["에스토니아", "Estonia", "", "Estonia"],
    ["에콰도르", "Ecuador", "593", "Ecuador"],
    ["에티오피아", "Ethiopia", "251", "ኢትዮጵያ"],
    ["엘살바도르", "El Salvador", "503", "El Salvador"],
    ["영국령 인도양", "British Indian Ocean Territory", "246", "British Indian Ocean Territory"],
    ["영연방 버진 군도", "Virgin Islands, British", "", "Virgin Islands, British"],
    ["예멘", "Yemen", "967", "اليمن"],
    ["오만 이슬람", "Oman", "968", "عُمان"],
    ["오스트레일리아", "Australia", "61", "Australia"],
    ["오스트리아", "Austria", "43", "Österreich"],
    ["온두라스", "Honduras", "504", "Honduras"],
    ["왈리스 앤드 푸투나", "Wallis and Futuna", "", "Wallis and Futuna"],
    ["요르단", "Jordan", "962", "الأردن"],
    ["우간다", "Uganda", "256", "Uganda"],
    ["우루과이", "Uruguay", "598", "Uruguay"],
    ["우즈베키스탄", "Uzbekistan", "998", "O‘zbekiston"],
    ["우크라이나", "Ukraine", "380", "Україна"],
    ["웨스턴 사라하", "Western Sahara", "", "Western Sahara"],
    ["이라크", "Iraq", "964", "العراق"],
    ["이란", "Iran, Islamic Republic of", "98", "ایران"],
    ["이스라엘", "Israel", "972", "ישראל"],
    ["이집트", "Egypt", "20", "مصر"],
    ["인도", "India", "91", "भारत"],
    ["인도네시아", "Indonesia", "62", "Indonesia"],
    ["자마이카", "Jamaica", "", "Jamaica"],
    ["잠비아", "Zambia", "", "Zambia"],
    ["적도 기니", "Equatorial Guinea", "", "Equatorial Guinea"],
    ["조지아", "Georgia", "", "Georgia"],
    ["중앙 아프리카 공화국", "Central African Republic", "", "Central African Republic"],
    ["지부티", "Djibouti", "", "Djibouti"],
    ["지브롤터", "Gibraltar", "", "Gibraltar"],
    ["짐바브웨", "Zimbabwe", "", "Zimbabwe"],
    ["챠드", "Chad", "", "Chad"],
    ["체코", "Czech Republic", "420", "Česko"],
    ["칠레", "Chile", "56", "Chile"],
    ["카메룬", "Cameroon", "237", "Cameroun"],
    ["카보 베르데", "Cape Verde", "", "Cape Verde"],
    ["카자흐스탄", "Kazakhstan", "", "Kazakhstan"],
    ["카타르국", "Qatar", "974", "قطر"],
    ["캄보디아", "Cambodia", "855", "កម្ពុជា"],
    ["캐나다", "Canada", "1", "Canada"],
    ["케냐", "Kenya", "254", "Kenya"],
    ["케이맨 제도", "Cayman Islands", "", "Cayman Islands"],
    ["코모로", "Comoros", "", "Comoros"],
    ["코스타리카", "Costa Rica", "506", "Costa Rica"],
    ["코코스 (킬링) 제도", "Cocos (Keeling) Islands", "", "Cocos (Keeling) Islands"],
    ["코트디부아르", "Cote d'Ivoire", "", "Cote d'Ivoire"],
    ["콜롬비아", "Colombia", "57", "Colombia"],
    ["콩고", "Congo", "242", "Congo"],
    ["콩고 민주 공화국", "Congo, the Democratic Republic of the", "243", "République démocratique du Congo"],
    ["쿠바", "Cuba", "53", "Cuba"],
    ["쿠웨이트", "Kuwait", "965", "الكويت"],
    ["쿡 제도", "Cook Islands", "", "Cook Islands"],
    ["크로아티아", "Croatia", "385", "Hrvatska"],
    ["크리스마스 섬", "Christmas Island", "", "Christmas Island"],
    ["키르기스스탄", "Kyrgyzstan", "996", "Кыргызстан"],
    ["키리바시", "Kiribati", "", "Kiribati"],
    ["키프로스", "Cyprus", "", "Cyprus"],
    ["타이", "Thailand", "66", "ประเทศไทย"],
    ["타지키스탄", "Tajikistan", "", "Tajikistan"],
    ["탄자니아 공화국", "Tanzania, United Republic of", "255", "Tanzania"],
    ["터키", "Turkey", "90", "Türkiye"],
    ["토고", "Togo", "", "Togo"],
    ["토켈라우 제도", "Tokelau", "", "Tokelau"],
    ["통가", "Tonga", "", "Tonga"],
    ["투르크 카이코스 제도", "Turks and Caicos Islands", "", "Turks and Caicos Islands"],
    ["투르크메니스탄", "Turkmenistan", "", "Turkmenistan"],
    ["투발루", "Tuvalu", "", "Tuvalu"],
    ["튀니지", "Tunisia", "", "Tunisia"],
    ["트리니다드 토바고", "Trinidad and Tobago", "", "Trinidad and Tobago"],
    ["파나마", "Panama", "507", "Panamá"],
    ["파라과이", "Paraguay", "595", "Paraguay"],
    ["파키스탄", "Pakistan", "92", "پاکستان"],
    ["파파뉴기니아", "Papua New Guinea", "", "Papua New Guinea"],
    ["팔라우", "Palau", "", "Palau"],
    ["팔레스타인", "Palestinian Territory, Occupied", "", "Palestinian Territory, Occupied"],
    ["페로 제도", "Faroe Islands", "", "Faroe Islands"],
    ["페루", "Peru", "51", "Perú"],
    ["포르투갈", "Portugal", "351", "Portugal"],
    ["포클랜드 제도 (몰비나)", "Falkland Islands (Malvinas)", "", "Falkland Islands (Malvinas)"],
    ["폴란드", "Poland", "48", "Polska"],
    ["푸에르토리코", "Puerto Rico", "", "Puerto Rico"],
    ["프랑스령 기아나", "French Guiana", "", "French Guiana"],
    ["프랑스령 남부 지역", "French Southern Territories", "", "French Southern Territories"],
    ["프랑스령 폴리네시아", "French Polynesia", "", "French Polynesia"],
    ["피지", "Fiji", "679", "Viti"],
    ["핀란드", "Finland", "358", "Suomi"],
    ["필리핀", "Philippines", "63", "Pilipinas"],
    ["핏케언 섬", "Pitcairn", "", "Pitcairn"],
    ["허드섬과 맥도널드 제도", "Heard Island and McDonald Islands", "", "Heard Island and McDonald Islands"],
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
// 검색용 별칭 (화면 표시/엑셀에는 쓰지 않음). 키는 COUNTRIES 의 한글명과 같아야 함
const COUNTRY_ALIASES = {
    '대한민국': ['south korea', 'republic of korea', '한국', '남한'],
    '북한': ['north korea', 'dprk'],
    '미국': ['usa', 'america', 'united states of america', '美国', '美國', 'アメリカ'],
    '영국': ['uk', 'england', 'britain', 'great britain', 'scotland', 'wales', 'northern ireland'],
    '베트남': ['vietnam'],
    '중국': ['prc', "people's republic of china", '中國'],
    '대만': ['taiwan', '台灣', '台湾', 'roc'],
    '마카오': ['macau', '澳門', '澳门'],
    '타이': ['태국', 'thai'],
    '오스트레일리아': ['호주', 'aussie'],
    '뉴질랜드': ['nz'],
    '아랍 에리레이트': ['uae', 'emirates', 'dubai', '아랍에미리트'],
    '체코': ['czechia'],
    '네델란드': ['holland', '네덜란드'],
    '터키': ['튀르키예'],
    '이란': ['persia'],
    '라오스': ['laos'],
    '미얀마': ['burma'],
    '콩고 민주 공화국': ['drc', 'dr congo'],
    '코트디부아르': ['ivory coast'],
    '스와질랜드': ['eswatini'],
    '마케도니아': ['north macedonia', '북마케도니아'],
    '동 티모르': ['timor-leste'],
    '바티칸 시국': ['vatican'],
    '남아프리카 공화국': ['rsa', '남아공'],
    '사우디 아라비아': ['ksa'],
    '벨로루시': ['벨라루스'],
    '아제르바이젠 공화국': ['아제르바이잔'],
    '니카라과이': ['니카라과'],
    '벨리제': ['벨리즈'],
    '챠드': ['차드'],
    '자마이카': ['자메이카'],
    '알제리아': ['알제리'],
    '시리안 아랍': ['시리아', 'syria'],
    '리위니옹': ['레위니옹'],
};

// query: 검색어 (한글명 / 영문명 / 현지어 / 별칭 어디에 포함돼도 일치, 대소문자 무시)
function buildCountryOptions(lang = 'native', query = '') {
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

    const q = query.trim().toLowerCase();
    if (q) {
        const matches = sorted.filter(c =>
            [c.ko, c.en, c.native, ...(COUNTRY_ALIASES[c.ko] || [])].some(s => s.toLowerCase().includes(q)));
        if (!matches.length) {
            return `<option value="" disabled selected>${lang === 'ko' ? '검색 결과 없음' : 'No results'}</option>`;
        }
        return `<option value="" disabled selected>${placeholder} (${matches.length})</option>` + matches.map(opt).join('');
    }

    return `
        <option value="" disabled selected>${placeholder}</option>
        <optgroup label="${lang === 'ko' ? '자주 선택' : 'Popular'}">
            ${POPULAR_COUNTRIES.map(k => opt(findCountry(k))).join('')}
        </optgroup>
        <optgroup label="${lang === 'ko' ? '전체' : 'All countries'}">
            ${sorted.map(opt).join('')}
        </optgroup>`;
}
// <select> 위에 국가 검색창을 붙인다. 입력할 때마다 목록을 다시 만들고,
// 결과가 1개면 바로 선택한다.
function attachCountrySearch(select, lang = 'native') {
    const input = document.createElement('input');
    input.type = 'search';
    input.className = 'form-control mb-2';
    input.autocomplete = 'off';
    input.placeholder = lang === 'ko' ? '🔍 국가 검색 (예: 영국, Japan)' : '🔍 Search country (e.g. Korea, 中国)';
    select.before(input);
    select.innerHTML = buildCountryOptions(lang);

    // 폼 안에 있으므로 Enter로 제출되지 않게 막는다
    input.addEventListener('keydown', e => { if (e.key === 'Enter') e.preventDefault(); });
    input.addEventListener('input', () => {
        const prev = select.value;
        select.innerHTML = buildCountryOptions(lang, input.value);
        const values = [...select.options].map(o => o.value).filter(Boolean);
        if (prev && values.includes(prev)) select.value = prev;
        else if (values.length === 1) select.value = values[0];
    });

    select.countrySearch = { input, lang };
    return input;
}

// 검색어를 지우고 전체 목록으로 되돌린 뒤 value 를 선택한다
function resetCountrySearch(select, value = '') {
    const s = select.countrySearch;
    if (s) {
        s.input.value = '';
        select.innerHTML = buildCountryOptions(s.lang);
    }
    select.value = value || '';
}
