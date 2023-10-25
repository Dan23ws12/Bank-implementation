CREATE TABLE AS BankAccount(
    username VARCHAR PRIMARY KEY,
    firstName VARCHAR,
    lastName VARCHAR,
    balance FLOAT CHECK (balance >= 0)
);
/* logs all transactions done and the date of transaction
*/
CREATE TABLE AS Transaction(
    amount FLOAT CHECK (amount >= 0),
    fromCus VARCHAR NOT NULL,
    toCus VARCHAR,
    date DATE,
    FOREIGN KEY (toCus, fromCus) REFERENCES BankAccount (username) 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

/* the trigger checking that no one transferres more than 1000 in a month
*/
-- the function associated with the trigger maxAmount
CREATE OR REPLACE FUNCTION maxAmount()
    RETURNS TRIGGER 
    AS 
$$   
BEGIN
    IF ((SELECT SUM(amount) FROM Transaction t WHERE 
        NEW.fromCus = t.fromCus AND 
        extract(year from NEW.date) = extract(year from t.date) AND
        extract(month FROM NEW.date) = extract (month FROM t.date)) > 1000) THEN
        RAISE EXCEPTION 'Cannot transfer more than $1000 per month';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER maxAmountTrigger
    BEFORE INSERT ON Transaction
    EXECUTE FUNCTION maxAmount();

