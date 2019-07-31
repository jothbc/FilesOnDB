@echo off
echo Realizando backup do MySQL...
cd C:\xampp\mysql\bin
mysqldump -u root teofilo> C:\xampp\backup\teofilo.sql
echo Backup concluído com sucesso.
