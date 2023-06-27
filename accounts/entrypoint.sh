#!/bin/bash

set -o errexit
set -o pipefail
set -o nounset

export DATABASE_URL="${DATABASE_URL}"
export REGISTRY_SERVICE_URL="${REGISTRY_SERVICE_URL}"

java -jar accounts-0.0.1.jar