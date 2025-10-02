# Task & Project Management App

### install postgres (if not installed)

`brew install postgresql`

### start postgres as a service

`brew services start postgresql`

### create a DB user and DB (open psql shell)

`psql postgres`

### then in psql run:

`CREATE ROLE admin WITH LOGIN PASSWORD 'secret';`  
`CREATE DATABASE taskapp_db OWNER admin;`  
`\q`
