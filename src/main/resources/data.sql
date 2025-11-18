# INSERT INTO guides (
#     id, login_id, password, name, birth, gender, created_at, nationality, phone
# ) VALUES (
#              UNHEX(REPLACE('11111111-1111-1111-1111-111111111111', '-', '')),  -- 이게 핵심!
#              'admin',
#              'admin123',
#              '최고관리자',
#              '1990-01-01',
#              'MALE',
#              NOW(),
#              'KOREA',
#              '010-1234-5678'
#          );
#
# INSERT INTO guides (
#     id, login_id, password, name, birth, gender, created_at, nationality, phone
# ) VALUES (
#              UNHEX(REPLACE('22222222-2222-2222-2222-222222222222', '-', '')),
#              'guide01',
#              '1234',
#              '김가이드',
#              '1995-05-05',
#              'MALE',
#              NOW(),
#              'KOREA',
#              '010-5678-1234'
#          );