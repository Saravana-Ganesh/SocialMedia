CREATE TABLE FRIEND_REQUEST_MASTER(
    FROM_USER VARCHAR2(100),
    TO_USER  VARCHAR2(100),
    STATUS INT,
    CONSTRAINT FK_FROM_USER FOREIGN KEY (FROM_USER)
    REFERENCES ACCOUNT_MASTER(EMAIL),
    CONSTRAINT FK_TO_USER FOREIGN KEY (TO_USER)
    REFERENCES ACCOUNT_MASTER(EMAIL)    
);
