DROP TABLE member;
CREATE TABLE member(
    mid         VARCHAR2(20),
    name        VARCHAR2(20),
    age         NUMBER(3),
    email       VARCHAR2(50),
    sex         VARCHAR2(10),
    birthday    DATE,
    note        CLOB,
    phone       VARCHAR2(20),
    CONSTRAINT pk_mid PRIMARY KEY(mid)
);