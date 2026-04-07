# Java SE / CDI / DeltaSpike Project Template

A minimal Java SE project template demonstrating CDI (Contexts and Dependency Injection) with
Apache DeltaSpike for container bootstrapping and lifecycle management.

## Architecture

- **CDI container**: Apache OpenWebBeans 4.0.3 (SE mode)
- **DeltaSpike 2.0.1**: provides `CdiContainer` / `CdiContainerLoader` for booting CDI outside an
  application server, plus the `BeanProvider` utility
- **Production beans**: `ApplicationScopedBean` (application-scoped CDI bean),
  `DemoApp` (entry point that boots the container and injects the bean)

## Requirements

- **Java 25** or later
- **Apache Maven 3.6.3** or later

## Build

```bash
mvn clean verify
```

This runs all quality gates (enforcer, checkstyle, RAT license check, compiler warnings,
JaCoCo coverage, and tests).

## Quality Plugins

| Plugin | Purpose |
|--------|---------|
| Compiler | `-Xlint:all`, fail on warnings |
| Enforcer | Java 25+, Maven 3.6.3+, dependency convergence, banned javax artifacts |
| Checkstyle | Code style validation (imports, braces, whitespace) |
| Apache RAT | Apache 2.0 license header verification |
| Surefire | Test execution |
| JaCoCo | Code coverage |
| Javadoc | Documentation generation |

## Testing

Tests use [dynamic-cdi-test-bean-addon](https://github.com/os890/dynamic-cdi-test-bean-addon)
with `@EnableTestBeans` to boot a CDI SE container, discover beans automatically, and inject
them into the test class. No manual container setup is required.

## License

This project is licensed under the [Apache License, Version 2.0](LICENSE).
