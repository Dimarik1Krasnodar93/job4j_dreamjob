ALTER TABLE users DROP CONSTRAINT IF exists email_unique;
ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);