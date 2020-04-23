create or replace PROCEDURE Identity_Signup_Master
IS
BEGIN
	update ACCOUNT_MASTER set UserId=(select max(UserId)+1 from ACCOUNT_MASTER) where UserId=0;
commit;
END;