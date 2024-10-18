## Для успешного завершения api-тестов:

**Создайте файл .env следующей структуры в корне модуля api-tests:**

```
VALID_USERNAME=LOGIN
VALID_PASSWORD=PASSWORD
INVALID_USERNAME=invalidUsername
INVALID_PASSWORD=invalidPassword
```

**Создайте файл config.properties следующей структуры в api-tests/src/test/resources:**
```declarative
base.url=http://9b142cdd34e.vps.myjino.ru:49268
valid.username=LOGIN
valid.password=PASSWORD
invalid.username=invaliduser
invalid.password=invalidpass
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
