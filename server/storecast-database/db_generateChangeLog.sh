#!/usr/bin/env bash
###############################################################################
# db_generateChangeLog.sh
#
# Perform a liquibase generate change log.
###############################################################################
script_base=$(dirname "$(readlink -f "${0}")")

function show_help()
{
    echo "USAGE: db_generateChangeLog.sh OUTPUT_FILE"
    echo "Perform a liquibase sql update."
    echo
    echo " -h    Show this help."
    echo
}

while getopts :h FLAG; do
    case $FLAG in
    h) show_help; exit ;;
    *)
        echo "Unrecognized option $OPTARG"
        show_help
        exit
        ;;
    esac
done

shift $((OPTIND-1))

if [ $# -eq 0 ]; then
    echo "OUTPUT_FILE was not specified"
    show_help
    exit 1
fi

change_log_file=$1

if [ -f $change_log_file ]; then
    echo "Change log file exists.  Removing"
    rm -i $change_log_file
fi

mvn liquibase:generateChangeLog -f "${script_base}/pom.xml" -Dliquibase.outputChangeLogFile="${change_log_file}"

if [ ! "$?" -eq "0" ]; then
    echo "Failed to create changelog"
    exit
fi

echo "Running ${change_log_file} through a couple of filters"

echo "Changing JSONB types"
sed -i "s/JSONB[(][0-9]*[)]/JSONB/g" $change_log_file


