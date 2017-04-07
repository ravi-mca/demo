#!/usr/bin/env bash
###############################################################################
# db_drop.sh
#
# Drop the database and the owning user.
###############################################################################
script_base=$(dirname "$(readlink -f "${0}")")

. "$script_base/db_configuration.sh"

function show_help()
{
    echo "USAGE: db_drop.sh [OPTIONS]"
    echo "Drop the database and the owning user."
    echo
    echo " -h    Show this help"
    echo " -d    The database to dump.  Defaults to '${DEFAULT_DB_NAME}'"
    echo " -H    The postgres host to connect to.  Defaults to '${DEFAULT_DB_HOST}'"
    echo " -U    Admin user to connect to the database with.  Defaults to '${DEFAULT_POSTGRES_USERNAME}'"
    echo " -P    Administrative users password to connect to to the database with.  Defaults to '${DEFAULT_POSTGRES_PASSWORD}'"
    echo
}

database_name=$DEFAULT_DB_NAME
database_host=$DEFAULT_DB_HOST
database_username=$DEFAULT_DB_USER
pg_username=$DEFAULT_POSTGRES_USERNAME
pg_password=$DEFAULT_POSTGRES_PASSWORD

while getopts :hd:H:U:P: FLAG; do
    case $FLAG in
    h) show_help; exit ;;
    d) database_name=$OPTARG ;;
    H) database_host=$OPTARG ;;
    U) pg_username=$OPTARG ;;
    P) pg_password=$OPTARG ;;
    *)
        echo "Unrecognized option $OPTARG"
        show_help
        exit
        ;;
    esac
done

export PGDATABASE=$DEFAULT_POSTGRES_DATABASE
export PGPASSWORD=$pg_password
export PGUSER=$pg_username
export PGHOST=$database_host

psql -c "DROP DATABASE ${database_name};"
psql -c "DROP USER ${database_username};"

