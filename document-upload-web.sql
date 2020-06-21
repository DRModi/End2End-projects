use docuploaddb;

create table document(
id BIGINT NOT NULL,
name VARCHAR(100) NOT NULL,
data BLOB NOT NULL,
PRIMARY KEY (id)
)


# Note: Alter the table, by default following are the data type: 
# For example for LOB's:
# TINYBLOB ≈ 255 bytes, BLOB ≈ 64KB, MEDIUMBLOB ≈ 16MB and LONGBLOB ≈ 4GB Run
# ALTER TABLE document MODIFY data MEDIUMBLOB;

# Issue: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'data' at row 1


drop table document
