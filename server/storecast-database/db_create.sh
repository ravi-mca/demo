###############################################################################
# db_create.sh
#
# Script will create a Postgres Database and owning user.
###############################################################################
script_base=$(dirname "$(readlink -f "${0}")")

. "$script_base/db_configuration.sh"

function show_help()
{
    echo "USAGE: db_create.sh [OPTIONS]"
    echo "Create a postgres database and the owning user."
    echo
    echo " -h    Show this help"
    echo " -d    The database to create.  Defaults to '${DEFAULT_DB_NAME}'"
    echo " -H    The postgres host to connect to.  Defaults to '${DEFAULT_DB_HOST}'"
    echo " -u    The username of the database owner.  Defaults to '${DEFAULT_DB_USER}'"
    echo " -p    The password of the database owner.  Defaults to '${DEFAULT_DB_PASSWORD}'"
    echo " -U    Administrative user to connect to the database with.  Defaults to '${DEFAULT_POSTGRES_USERNAME}'"
    echo " -P    Administrative users password to connect to to the database with.  Defaults to '${DEFAULT_POSTGRES_PASSWORD}'"
    echo
}

database_name=$DEFAULT_DB_NAME
database_host=$DEFAULT_DB_HOST
database_username=$DEFAULT_DB_USER
database_password=$DEFAULT_DB_PASSWORD
pg_username=$DEFAULT_POSTGRES_USERNAME
pg_password=$DEFAULT_POSTGRES_PASSWORD

while getopts :hd:H:u:p:U:P: FLAG; do
    case $FLAG in
    h) show_help; exit ;;
    d) database_name=$OPTARG ;;
    H) database_host=$OPTARG ;;
    u) database_username=$OPTARG ;;
    p) database_password=$OPTARG ;;
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

psql -c "CREATE USER ${database_username} WITH ENCRYPTED PASSWORD '${database_password}';"
psql -c "ALTER USER ${database_username} CREATEDB;"

export PGUSER=$database_username
export PGPASSWORD=$database_password

psql -c "CREATE DATABASE ${database_name} ENCODING = 'UTF8';"

