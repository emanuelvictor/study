DROP TABLE IF EXISTS teste CASCADE;
DROP FUNCTION IF EXISTS teste_stamp();

CREATE TABLE teste (
                     nome text,
                     rascunho boolean
);

CREATE FUNCTION teste_stamp() RETURNS trigger AS $teste_stamp$
BEGIN
  IF NEW.rascunho IS FALSE THEN
    -- Check that nome and salary are given
    IF NEW.nome IS NULL THEN
      RAISE EXCEPTION 'nome cannot be null';
    END IF;
  END IF;
  RETURN NEW;
END;
$teste_stamp$ LANGUAGE plpgsql;

CREATE TRIGGER teste_stamp BEFORE INSERT OR UPDATE ON teste
  FOR EACH ROW EXECUTE PROCEDURE teste_stamp();


INSERT INTO teste (nome, rascunho) VALUES ('nome', true);

INSERT INTO teste (nome, rascunho) VALUES ('nome', false);

INSERT INTO teste (nome, rascunho) VALUES (null, false);

INSERT INTO teste (nome, rascunho) VALUES (null, true);

SELECT * FROM teste;
