--:name get-valid-by-id :? :1
SELECT *
FROM tokens
WHERE id = :v:token
AND "valid-to" > CURRENT_TIMESTAMP

--:name create! :!
INSERT INTO tokens (id, "user-id", "valid-to")
VALUES (:v:token, :v:user-id, :v:valid-to)

--:name remove-invalid! :!
DELETE FROM tokens
WHERE "valid-to" < CURRENT_TIMESTAMP
