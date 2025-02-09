INSERT INTO body_types (id, name)
VALUES (1, '상체'),
       (2, '하체'),
       (3, '전신');

INSERT INTO muscle_groups (id, body_type_id, name)
VALUES (1, 1, '가슴'), -- 상체 가슴
       (2, 1, '등'),  -- 상체 등
       (3, 1, '어깨'), -- 상체 어깨
       (4, 2, '하체'), -- 하체
       (5, 3, '복근'), -- 전신 복근
       (6, 1, '팔'),  -- 상체 팔
       (7, 3, '전신'); -- 전신 운동

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
       (18, '덤벨 로우', NOW(), NOW());

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
       (18, 2, 18); -- 덤벨 로우 등 운동

INSERT INTO users (email, username, password, role_name, provider, provider_id, created_at, updated_at)
VALUES ('xxx_@kakao.com',
        'user',
        '$2a$10$MDQHb9OlZIVY.I5e53TD5eUvvsV8QyIQt/wWkr9/AANBCzW02TFeC',
        'USER',
        NULL,
        NULL,
        NOW(),
        NOW());