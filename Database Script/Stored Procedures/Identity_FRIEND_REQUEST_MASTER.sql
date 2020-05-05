create or replace PROCEDURE Identity_FRIEND_REQUEST_MASTER
IS
BEGIN
	update FRIEND_REQUEST_MASTER set ID=(select max(ID)+1 from FRIEND_REQUEST_MASTER) where ID=0;
    commit;
END;

