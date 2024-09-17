## Для запуска тестов выполнить:

**Все тесты:**

    mvn clean test
    или
    mvn clean test -Pall-tests

**Только api-tests:**

    mvn clean -pl api-tests test
    или
    mvn clean test -Papi-tests

**Только ui-tests:**

    mvn clean -pl ui-tests test
    или
    mvn clean test -Papi-tests

**Smoke-тесты:**

    mvn clean test -Psmoke-tests

## Для генерации отчетов allure выполнить:

**Выполнить тесты:**

    mvn clean test

**Сгенерировать отчет:**

    mvn allure:report

**Открыть отчет:**

    mvn allure:serve
