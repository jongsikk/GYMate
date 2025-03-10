INSERT INTO body_types (id, name)
VALUES (1, '상체'),
       (2, '하체'),
       (3, '전신');

INSERT INTO muscle_groups (id, body_type_id, name)
VALUES (1, 1, '가슴'),
       (2, 1, '등'),
       (3, 1, '어깨'),
       (4, 2, '하체'),
       (5, 3, '복근'),
       (6, 1, '팔'),
       (7, 3, '전신');

INSERT INTO exercises (id, name, created_at, updated_at)
VALUES (1, '벤치프레스', NOW(), NOW()),
       (2, '랫풀다운', NOW(), NOW()),
       (3, '스쿼트', NOW(), NOW()),
       (4, '데드리프트', NOW(), NOW()),
       (5, '푸쉬업', NOW(), NOW()),
       (6, '플랭크', NOW(), NOW()),
       (7, '덤벨 벤치프레스', NOW(), NOW()),
       (8, '바벨 로우', NOW(), NOW()),
       (9, '런지', NOW(), NOW()),
       (10, '푸시업', NOW(), NOW()),
       (11, '사이드 레터럴 레이즈', NOW(), NOW()),
       (12, '힙 쓰러스트', NOW(), NOW()),
       (13, '크런치', NOW(), NOW()),
       (14, '버피', NOW(), NOW()),
       (15, '트라이셉스 딥스', NOW(), NOW()),
       (16, '바벨 스쿼트', NOW(), NOW()),
       (17, '스탠딩 카프 레이즈', NOW(), NOW()),
       (18, '덤벨 로우', NOW(), NOW()),
       (19, '체스트 프레스', NOW(), NOW()),
       (20, '딥스', NOW(), NOW()),
       (21, '풀업', NOW(), NOW()),
       (22, '페이스 풀', NOW(), NOW()),
       (23, '숄더 프레스', NOW(), NOW()),
       (24, '푸쉬 프레스', NOW(), NOW()),
       (25, '해머 컬', NOW(), NOW()),
       (26, '바벨 컬', NOW(), NOW()),
       (27, '레그 프레스', NOW(), NOW()),
       (28, '레그 컬', NOW(), NOW()),
       (29, '레그 익스텐션', NOW(), NOW()),
       (30, '카프 레이즈', NOW(), NOW()),
       (31, '불가리안 스플릿 스쿼트', NOW(), NOW()),
       (32, '레그 레이즈', NOW(), NOW()),
       (33, '러시안 트위스트', NOW(), NOW()),
       (34, '행잉 레그 레이즈', NOW(), NOW()),
       (35, '마운틴 클라이머', NOW(), NOW()),
       (36, '케틀벨 스윙', NOW(), NOW()),
       (37, '클린 앤 저크', NOW(), NOW()),
       (38, '스내치', NOW(), NOW()),
       (39, '배틀 로프', NOW(), NOW());

INSERT INTO exercise_muscle_groups (id, muscle_group_id, exercise_id)
VALUES (1, 1, 1),   -- 벤치프레스 가슴 운동
       (2, 2, 2),   -- 랫풀다운 등 운동
       (3, 3, 1),   -- 덤벨 벤치프레스 가슴 운동
       (4, 4, 3),   -- 스쿼트는 하체 운동
       (5, 4, 4),   -- 데드리프트 하체 운동
       (6, 5, 5),   -- 푸쉬업 가슴 운동
       (7, 6, 6),   -- 플랭크 복근 운동
       (8, 5, 10),  -- 푸시업 팔 운동
       (9, 4, 9),   -- 런지 하체 운동
       (10, 3, 11), -- 사이드 레터럴 레이즈 어깨 운동
       (11, 4, 12), -- 힙 쓰러스트 하체 운동
       (12, 5, 13), -- 크런치 복근 운동
       (13, 7, 14), -- 버피 전신 운동
       (14, 6, 15), -- 트라이셉스 딥스 팔 운동
       (15, 4, 16), -- 바벨 스쿼트 하체 운동
       (16, 4, 17), -- 스탠딩 카프 레이즈 하체 운동
       (17, 2, 4),  -- 데드리프트 등 운동
       (18, 2, 18), -- 덤벨 로우 등 운동
       (19, 1, 19), -- 체스트 프레스 가슴 운동
       (20, 1, 20), -- 딥스 가슴 운동
       (21, 2, 21), -- 풀업 등 운동
       (22, 3, 22), -- 페이스 풀 어깨 운동
       (23, 3, 23), -- 숄더 프레스 어깨 운동
       (24, 3, 24), -- 푸쉬 프레스 어깨 운동
       (25, 6, 25), -- 해머 컬 팔 운동
       (26, 6, 26), -- 바벨 컬 팔 운동
       (27, 4, 27), -- 레그 프레스 하체 운동
       (28, 4, 28), -- 레그 컬 하체 운동
       (29, 4, 29), -- 레그 익스텐션 하체 운동
       (30, 4, 30), -- 카프 레이즈 하체 운동
       (31, 4, 31), -- 불가리안 스플릿 스쿼트 하체 운동
       (32, 5, 32), -- 레그 레이즈 복근 운동
       (33, 5, 33), -- 러시안 트위스트 복근 운동
       (34, 5, 34), -- 행잉 레그 레이즈 복근 운동
       (35, 5, 35), -- 마운틴 클라이머 복근 운동
       (36, 7, 36), -- 케틀벨 스윙 전신 운동
       (37, 7, 37), -- 클린 앤 저크 전신 운동
       (38, 7, 38), -- 스내치 전신 운동
       (39, 7, 39); -- 배틀 로프 전신 운동

INSERT INTO users (email, username, password, role_name, provider, provider_id, created_at, updated_at)
VALUES ('xxx_@kakao.com',
        'user',
        '$2a$10$MDQHb9OlZIVY.I5e53TD5eUvvsV8QyIQt/wWkr9/AANBCzW02TFeC',
        'USER',
        NULL,
        NULL,
        NOW(),
        NOW());