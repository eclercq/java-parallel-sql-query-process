FROM alpine:latest

RUN apk add --no-cache postgresql-client
COPY sql/ /sql
ENV PGPASSWORD=password

CMD ["sh", "-c", "psql -h parallel-data-process-database -U postgres -d db -f /sql/init.sql"]