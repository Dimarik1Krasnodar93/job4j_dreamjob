CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created timestamp,
   visible bool,
   id_city int,
   photo bytea
);

