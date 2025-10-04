
# Simple Banking Application

SDAT & DevOps **QAP 1** — Java + Maven project with **JUnit 5** tests and **GitHub Actions** CI.

## Features
- Create account with holder name and initial balance
- Deposit and withdraw with validations
- Check current balance

## Project Structure
```
simple-banking-app/
  ├─ .github/workflows/maven.yml     # GitHub Actions to run tests on PR
  ├─ src/main/java/com/example/bank  # Application source
  ├─ src/test/java/com/example/bank  # Unit tests (JUnit 5)
  ├─ pom.xml                         # Maven config & dependencies
  └─ README.md
```

## Run Locally
```bash
mvn -v           # ensure Maven is installed
mvn test         # run tests
mvn -q -DskipTests package
```

## Clean Code Examples (from this repo)
1) **Small, focused classes & methods**: `Account` encapsulates state and invariants; `BankService` orchestrates operations.
2) **Meaningful names**: `deposit`, `withdraw`, `getBalance`, `InsufficientFundsException` — self-explanatory semantics.
3) **Defensive validation**: constructors and methods check preconditions (null/blank names, negative amounts), throwing clear exceptions.
4) **Immutability where possible**: account `id` and `holderName` are final; only balance mutates in a controlled way.
5) **Single responsibility**: `Main` only handles a simple CLI demo; business logic stays out of the UI.

## Tests
- `AccountTest` covers positive and negative scenarios with a mix of assertions:
  - deposit increases balance
  - withdraw decreases balance
  - withdrawing more than balance throws `InsufficientFundsException`
  - negative/zero amounts are rejected

Run:
```bash
mvn test
```

## Dependencies
- **JUnit 5 (org.junit.jupiter:junit-jupiter)** — test framework (from Maven Central).
- Maven Surefire Plugin to run JUnit 5 tests.

## GitHub Actions (CI)
Workflow triggers on **pull_request** to run `mvn -B -ntp test`.  
See `.github/workflows/maven.yml`.

## Dev/Trunk Based Workflow
- Create feature branches from `main` (e.g., `feat/deposit-limits`).
- Commit small, frequent changes.
- Open a **Pull Request** — CI will run tests automatically.
- Merge via PR to keep `main` green.

## Problems & Notes (to edit)
- If CI fails due to environment/Java version, we pin JDK 17 in the workflow.
- Add any blockers you hit here during submission.

---

**How it works:** Each `Account` maintains its balance; `BankService` provides simple factory and operations.
