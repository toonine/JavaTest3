FROM registry.saas.hand-china.com/tools/mysql:5.6
COPY sakila-schema.sql /docker-entrypoint-initdb.d
COPY sakila-data.sql /docker-entrypoint-initdb.d
# 不知道如何导入文件