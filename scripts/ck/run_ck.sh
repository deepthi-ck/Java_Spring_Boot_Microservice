#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
cd "$ROOT"
mkdir -p ck-reports
if [[ ! -f ck.jar ]]; then
  echo "Download CK jar as ck.jar (mauricioaniche/ck) then re-run."
  exit 1
fi
java -jar ck.jar . false 0 false ./ck-reports