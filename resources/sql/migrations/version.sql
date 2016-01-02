--:name setup-versions! :!
CREATE TABLE IF NOT EXISTS versions (
       version bigint PRIMARY KEY
)

--:name all-versions :? :*
SELECT version FROM versions

--:name install-version! :!
INSERT INTO versions (version) VALUES (:v:version)

--:name uninstall-version! :!
DELETE FROM versions
WHERE version = :v:version
