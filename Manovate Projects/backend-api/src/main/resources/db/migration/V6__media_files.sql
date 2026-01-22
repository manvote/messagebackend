CREATE TABLE media_files (
    id BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(100),
    file_size BIGINT,
    file_url VARCHAR(255),
    uploaded_by BIGINT NOT NULL,
    uploaded_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_media_user
      FOREIGN KEY (uploaded_by)
      REFERENCES users(id)
      ON DELETE CASCADE
);
