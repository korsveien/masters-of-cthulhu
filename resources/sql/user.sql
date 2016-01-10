--:name get-by-email :? :1
SELECT :i*:fields
FROM users
WHERE email = :v:email

--:name get-by-token :? :1
SELECT :i*:fields
FROM users
JOIN tokens ON users.id = tokens."user-id"
WHERE tokens.id = :v:token
AND tokens."valid-to" > CURRENT_TIMESTAMP

--:name create<! :? :1
INSERT INTO users (email)
VALUES (:v:email)
RETURNING id

--:name update-logged-in! :!
UPDATE users
SET "logged-in" = CURRENT_TIMESTAMP
WHERE id = :v:id
