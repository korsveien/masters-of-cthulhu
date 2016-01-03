--:name m-setup-users! :!
CREATE TABLE users (
       id serial PRIMARY KEY,
       name text,
       email text UNIQUE NOT NULL,
       password text,
       created timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
       modified timestamp with time zone,
       "logged-in" timestamp with time zone
)

--:name m-drop-users! :!
DROP TABLE users
