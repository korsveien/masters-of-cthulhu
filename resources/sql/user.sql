--:name get-by-id :? :1
SELECT *
FROM users
WHERE id = :v:id

--:name get-by-email :? :1
SELECT *
FROM users
WHERE email = :v:email

--:name create<! :? :1
INSERT INTO users (email)
VALUES (:v:email)
RETURNING id

--:name update-logged-in! :!
UPDATE users
SET "logged-in" = CURRENT_TIMESTAMP
WHERE id = :v:id
