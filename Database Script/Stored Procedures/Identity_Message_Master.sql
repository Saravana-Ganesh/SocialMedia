create or replace PROCEDURE Identity_Message_Master
IS
BEGIN
	update message_master set id=(select max(id)+1 from message_master) where id=0;
commit;
END;
