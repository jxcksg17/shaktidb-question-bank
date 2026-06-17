CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE subjects(
    subject_id SERIAL PRIMARY KEY,
    subject_name VARCHAR(100) UNIQUE
);

CREATE TABLE questions(
    question_id SERIAL PRIMARY KEY,
    subject_id INT REFERENCES subjects(subject_id),
    difficulty VARCHAR(20),
    question_text TEXT
);

CREATE TABLE audit_logs(
    log_id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    action VARCHAR(200),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
