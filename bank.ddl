CREATE TABLE AS BankAccount(
    VARCHAR customerID PRIMARY KEY,
    VARCHAR name,
    FLOAT balance CHECK (balance >= 0)
);
/* logs all transactions done and the date of transaction
*/
CREATE TABLE AS Transaction(
    FLOAT amount CHECK (amount >= 0),
    VARCHAR fromCus NOT NULL,
    VARCHAR toCus,
    DATE date,
    FOREIGN KEY (toCus, fromCus) REFERENCES BankAccount (customerID) 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


-- the function associated with the trigger maxAmount
CREATE FUNCTION maxAmount()
    RETURN TRIGGER AS $maxAmountTrigger$
    LANGUAGE PLPGSQL
        BEGIN
            IF ((SELECT COUNT(amount) FROM Transaction t WHERE 
                NEW.fromCus = t.fromCus AND 
                extract(year from NEW.date) = extract(year from t.date) AND
                extract(month FROM NEW.date) = extract (month FROM t.date)) > 1000)
                RAISE EXCEPTION "Cannot transfer more than $1000 per month"
            END IF;
            RETURN NEW;
        END;
    $maxAmountTrigger$
/* the trigger checking that no one transferres more than 1000 in a month
*/
CREATE TRIGGER maxAmountTrigger
    BEFORE INSERT ON Transaction
    EXECUTE FUNCTION maxAmount();


