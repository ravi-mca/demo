#!/usr/bin/env bash
###############################################################################
# db_reset.sh
#
# Drop, create and update the default database.
###############################################################################
script_base=$(dirname "$(readlink -f "${0}")")

. "${script_base}/db_drop.sh"
. "${script_base}/db_create.sh"
. "${script_base}/db_update.sh"


