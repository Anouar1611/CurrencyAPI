# Create a currency converter

## Your task

![mockup](./task-mockup.png)

### 1. Currency converter

You have a basic spring-boot application. You can open the web interface using: http://localhost:8080/

Please create a currency converter website which will have input fields for amount and from/to currency selection you. The website should look like the mockup above.

The website does not have to be beautiful, but it must be functional.

* Design your own currency conversion API that is accepting client input and returns the converted value
* Nice to have: use caching in your app to limit the number of exchange-rates API calls
* The inputs should only allow numbers and comma. The values have 2 decimal places and are separated by commas.
* You can fetch the symbols and latest rates from this API: http://localhost:9090/currencies
* Please use this endpoint: we expect you to implement conversion based on latest rates yourself.
* For accessing it, you need to run the following command:
```shell
java -jar forex.jar
```

### 2. Currencies full names

* Replace the currency abbreviations with their full names. For this purpose you can use the REST API at https://openexchangerates.org/api/currencies.json
* HINT: match only the currencies you got from point 1.
* Sort the currencies full names alphabetically.

### Additional points

Please create a new branch with your solution and open a PR in bitbucket once you are done.

These are some things we would like you to focus on:

* a nice clean design with clear separation of concerns
* good test coverage
* if you have a preferred way of committing (many small commits or one big commit with all changes) please show us this
* a readme file with a short explanation of your solution would be very nice too
* focus on your strong points: if you feel like you have more to show on some of these points than others, please focus on those and try to impress us
