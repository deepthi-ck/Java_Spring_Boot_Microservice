# Integrated quality tools (Java 8 microservices)

Tools are wired at the Maven parent (`pom.xml`) and shared `config/` + `scripts/`.
They apply across the multi-module reactor — **not** separate top-level tool fixture folders.

| Tool | Location | Command |
|------|----------|---------|
| Checkstyle | `config/checkstyle/` + Maven plugin | `./mvnw checkstyle:check` |
| PMD | `config/pmd/ruleset.xml` + Maven plugin | `./mvnw pmd:check` |
| CPD | Maven PMD plugin `cpd-check` | `./mvnw pmd:cpd-check` |
| SpotBugs | `config/spotbugs/` + Maven plugin | `./mvnw spotbugs:check` |
| JaCoCo | Maven plugin (per module) | `./mvnw test jacoco:report` |
| PIT | Maven plugin | `./mvnw org.pitest:pitest-maven:mutationCoverage` |
| OWASP-Dependency-Check | Maven plugin | `./mvnw org.owasp:dependency-check-maven:check` |
| diff-cover | Maven plugin | `./mvnw diff-coverage:report` |
| CK | `scripts/ck/run_ck.sh` | `bash scripts/ck/run_ck.sh` |
| Git | `scripts/git/git_churn.py` | `python scripts/git/git_churn.py` |
| Static-DU-JaCoCo-composite | `config/pmd/static-du-ruleset.xml` + profile | `./mvnw verify -Pstatic-du-jacoco-composite` |

Java toolchain: `maven-compiler-plugin` (`source/target=1.8`), Surefire, Spring Boot Maven plugin per service module.