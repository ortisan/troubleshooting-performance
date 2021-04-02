CREATE TABLE stock_quote
(
    id     INT AUTO_INCREMENT,
    symbol VARCHAR(10) NOT NULL,
    date   TIMESTAMP   NOT NULL,
    high   FLOAT DEFAULT NULL,
    low    FLOAT DEFAULT NULL,
    open   FLOAT DEFAULT NULL,
    close  FLOAT DEFAULT NULL,
    volume INT   DEFAULT NULL,
    PRIMARY KEY (id)
)