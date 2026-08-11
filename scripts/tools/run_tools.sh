#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
cd "$ROOT"
./mvnw -q clean test jacoco:report
./mvnw -q checkstyle:check pmd:check pmd:cpd-check spotbugs:check
./mvnw -q org.pitest:pitest-maven:mutationCoverage || true
./mvnw -q org.owasp:dependency-check-maven:check || true
./mvnw -q diff-coverage:report || true
./mvnw -q verify -Pstatic-du-jacoco-composite || true
python scripts/git/git_churn.py || true
bash scripts/ck/run_ck.sh || true
echo "Tool pipeline finished (Java 21 microservices)."