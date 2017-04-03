#!/usr/bin/env bash
###############################################################################
# db_update.sh
#
# Perform a liquibase update on the default database.
###############################################################################
script_base=$(dirname "$(readlink -f "${0}")")

mvn liquibase:update -f "${script_base}/pom.xml"
