# follow-the-money

Budget sharing and group expense management.

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

> **Change of mind log**: I tried to migrate to duct framework, but making it run is another workflow. It works, and it gives me some power to debug with some macros, as well as some script tools to run stuff, but I need to understand the workflow better, since I can work better with Lein (it's closer to what I already do in my job).


