create or replace PROCEDURE Identity_Signup_Master
IS
BEGIN
	update User_Signup_Master set UserId=(select max(UserId)+1 from User_Signup_Master) where UserId=0;
commit;
END;