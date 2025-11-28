# follow-the-money

Budget sharing and group expense management.

## Technical details
- Using [Sierra's Reloaded workflow](https://cognitect.com/blog/2013/06/04/clojure-workflow-reloaded)
- Will integrate component library and probably will add some HTTP/router/web libraries to make it a web service
- Also intend to add some database component, probably Datomic (for learning purposes). TBD

## Decision Records

#### 01st Nov 2025
- Started the project with a Sierra's Reloaded framework lein project

#### 05th Nov 2025
To make it simpler, I'll add clojure.spec only to external layer's schemas:
- Request models
- Response models
- Database models
This way I'll validate API contracts and persisted data, but will keep the simplicity of working with simple maps in the domain layer.

#### 25th Nov 2025
Overall it has been interesting to try to use clojure.spec to help me validate contracts and schemas that goes through the layers
- Added clojure.spec and created documentation for functions in adapters and services `(s/fdef)`
    - These `(s/fdef)` are for documentation only, they don't explicitly validate schemas
    - To explicitly validate schemas, I need to use `:pre` and `:post` inside the function definition to validate args and return
- Added `(stest/instrument)` call in `dev.clj` to only instrument schema validation during development/testing times.
    - This is good practice, as contracts are only for development when in **business and more internal layers**.
    - For edge contracts (eg. APIs), I can use the explicit `:pre` and `:post` or any kind of logic using `(s/valid?)` with some control over code flow
- Added schema spec also for business layer model (group). This makes it easier to translate between layers and validate the translation throughout the code

> **Change of mind log**: I tried to migrate to duct framework, but making it run is another workflow. It works, and it gives me some power to debug with some macros, as well as some script tools to run stuff, but I need to understand the workflow better, since I can work better with Lein (it's closer to what I already do in my job).

#### 26th Nov 2025
Decided to start working on the user modules, to have the following features:
- Create an account
- Access acount with email + password
- Create a new group
    - automatically join it (this will be another feature)
After this I can start thinking about:
- How to add other users to the group
- How to add expenses to a group
- How do I split expenses between users in the group
- How to pay due amounts (this due amount is just an aggregate, I don't need to worry about which expenses were added, I just need to pay up to the full amount)

#### 28th Nov 2025
After trying to create user adapters unit tests, I was failing due to the ::email attribute. It needed a custom generator, as per in this [clojure spec doc reference](https://clojure.org/guides/spec#_custom_generators). With this I managed to create a very simple generator, since all I need are valid and random email addresses to be generated for tests. But very interesting the workings of the `clojure.spec.gen.alpha` namespace, it allows to customize the specs a lot to guarantee data integrity.
