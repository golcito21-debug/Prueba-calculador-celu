plugins {
    kotlin("jvm") version "1.9.24"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        kotlin.srcDirs("app/src/main/kotlin")
    }
    test {
        kotlin.srcDirs("app/src/test/kotlin")
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.example.calculador.MainKt")
}
