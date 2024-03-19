# Java module loader
Java Module loader is a framework to load .jar archives with Java modules and to introduce them as modules into a basic software.

## Use with Gradle
```gradle
repositories {
    maven {
        url 'https://repo.interguessweb.de/maven-public/'
    }
}
```

```gradle
dependencies {
    implementation 'de.interguess:java-module-loader:latest'
}
```

## Use with Maven
```xml
<repositories>
    <repository>
        <id>interguess-repo</id>
        <url>https://repo.interguessweb.de/maven-public/</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
        <groupId>de.interguess</groupId>
        <artifactId>java-module-loader</artifactId>
        <version>latest</version>
    </dependency>
</dependencies>
```
