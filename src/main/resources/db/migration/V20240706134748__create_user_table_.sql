-- Migration template for user
CREATE TABLE user (
    id UUID PRIMARY KEY,
    createdAt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updatedBy VARCHAR(255) NOT NULL
);
-- Add your SQL migration scripts here
