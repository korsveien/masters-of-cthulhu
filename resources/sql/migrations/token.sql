--:name m-setup-tokens! :!
CREATE TABLE tokens (
       id uuid PRIMARY KEY,
       "user-id" integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
       "valid-to" timestamp with time zone NOT NULL
)

--:name m-drop-tokens! :!
DROP TABLE tokens
