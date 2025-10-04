# Simple Banking Application (SDAT & DevOps — QAP 1)

A minimal Java application showcasing clean code, JUnit 5 tests, and GitHub Actions CI.

## Features
- Account creation with initial balance
- Deposit & withdrawal with validation
- Transfer between accounts
- Custom exceptions for common error scenarios

## Project Structure
```
src/
  main/java/com/example/bank/
    Account.java
    BankService.java
    AccountNotFoundException.java
    InvalidAmountException.java
    InsufficientFundsException.java
  test/java/com/example/bank/
    AccountTest.java
    BankServiceTest.java
.github/workflows/maven.yml
pom.xml
```

## How to Run Locally
```bash
# run tests
mvn test

# package (runs tests)
mvn -B package
```

## GitHub Actions
Workflow: `.github/workflows/maven.yml` runs on every Pull Request to `main`:
- Checks out code
- Sets up JDK 17 (Temurin)
- Builds project with Maven (runs tests)

## Clean Code Examples (3+)
1. **Single Responsibility (Account)** — balance operations live in `Account` and only validate amounts; business coordination (e.g., transfers) lives in `BankService`.
2. **Meaningful Names** — `createAccount`, `getAccount`, `transfer`, `InvalidAmountException`, etc. self-document their intent.
3. **Fail Fast with Custom Exceptions** — invalid/insufficient amounts and missing accounts use domain exceptions (`InvalidAmountException`, `InsufficientFundsException`, `AccountNotFoundException`) rather than generic ones.
4. **Immutability of Identity** — `Account#id` and `owner` are `final` and never change; only `balance` mutates in a controlled manner.
5. **Use of BigDecimal** — monetary values use `BigDecimal` to avoid floating point inaccuracies.

## Tests
- `AccountTest`
  - deposit increases balance (positive)
  - withdraw decreases balance (positive)
  - withdraw throws on insufficient funds (negative)
- `BankServiceTest`
  - transfer moves funds from A to B (positive)
  - transfer with non-existing account throws (negative)
  - transfer with non-positive amount throws (negative)

## Dependencies
- JUnit 5 (`org.junit.jupiter:junit-jupiter:${junit.jupiter.version}`) — from Maven Central
- `maven-surefire-plugin` to run JUnit 5

## Trunk/Dev Workflow (suggested)
1. Create a feature branch
2. Commit small changes frequently with clear messages
3. Open a Pull Request into `main`
4. CI runs tests automatically on PR

## Notes / Troubleshooting
- If GitHub Actions fails on JDK, ensure Java 17 is selected in the workflow.
- If tests don’t discover, verify package names and that test classes end with `*Test`.


Trigger CI: 2025-10-04T10:18:17.7566548-02:30
