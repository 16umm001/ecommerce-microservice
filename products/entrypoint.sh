#!/bin/bash

set -o errexit
set -o pipefail
set -o nounset

export PRODUCT_DATABASE_URL="${PRODUCT_DATABASE_URL}"
export REGISTRY_SERVICE_URL="${REGISTRY_SERVICE_URL}"

java -jar products-0.0.1.jar