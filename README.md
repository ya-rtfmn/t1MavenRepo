## Для успешного завершения api-тестов:

**Создайте файл .env следующей структуры в корне модуля api-tests:**

```
VALID_USERNAME=LOGIN
VALID_PASSWORD=PASSWORD
```

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
