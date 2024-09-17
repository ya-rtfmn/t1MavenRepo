## Для запуска тестов выполнить:

**Все тесты:**

    mvn clean test

**Только api-tests:**

    mvn clean -pl api-tests test

**Только ui-tests:**

    mvn clean -pl ui-tests test

**Smoke-тесты:**

    mvn clean test -Psmoke-tests

## Для генерации отчетов allure выполнить:

**Выполнить тесты:**

    mvn clean test

**Сгенерировать отчет:**

    mvn allure:report

**Открыть отчет:**

    mvn allure:serve
