# KrunTime

A [Sponge](https://spongepowered.org) plugin providing the [Kotlin](https://kotlinlang.org/) runtime, standard library,
and reflection library to other plugins. 

## Depending on KrunTime - Build System

Follow the instructions below to depend on kotlin.

- [Gradle](https://kotlinlang.org/docs/reference/using-gradle.html)
- [Maven](https://kotlinlang.org/docs/reference/using-maven.html)

## Depending on KrunTime - SpongeGradle

In order to make sure your plugin both requires and loads after KrunTime, add this to your SpongeGradle configuration
```kotlin
dependency("kruntime") {
    version("0.1.0")
    loadOrder(PluginDependency.LoadOrder.AFTER)
    optional(false)
}
```

## Depending on KrunTime - sponge_plugins.json

```
"dependencies": [
    {
      ...
    },
    {
      "id": "kruntime",
      "version": "0.1.0",
      "load-order": "after",
      "optional": false
    }
]

```
