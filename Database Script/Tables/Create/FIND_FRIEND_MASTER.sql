CREATE TABLE FIND_FRIEND_MASTER(
    USER_MAIL VARCHAR(100),
    SUGGESTION_MAIL VARCHAR(100),
    CONSTRAINT FK_USER_MAIL FOREIGN KEY (USER_MAIL)
    REFERENCES ACCOUNT_MASTER(EMAIL),
    CONSTRAINT FK_SUGGESTION_MAIL FOREIGN KEY (SUGGESTION_MAIL)
    REFERENCES ACCOUNT_MASTER(EMAIL)   
)