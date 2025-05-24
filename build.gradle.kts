plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.gradleup.shadow") version "8.3.6"
}

group = "io.github.pcl-community"
version = "0.0.1"

repositories {
    maven { setUrl("https://maven.aliyun.com/repository/central") }
    maven { setUrl("https://maven.aliyun.com/repository/jcenter") }
    maven { setUrl("https://maven.aliyun.com/repository/google") }
    maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { setUrl("https://maven.aliyun.com/repository/public") }
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainClass.set("io.github.pclcommunity.pclfx.Main")
}

javafx {
    version = "17"
    modules = listOf("javafx.fxml", "javafx.controls", "javafx.graphics", "javafx.base", "javafx.web")
}

dependencies {
    implementation("commons-io:commons-io:2.19.0")
    implementation("com.google.code.gson:gson:2.13.0")
    implementation("org.jetbrains:annotations:24.0.0")

    //JavaFX
    implementation("org.controlsfx:controlsfx:11.2.1")
    implementation("com.github.goxr3plus:FX-BorderlessScene:4.4.0")

    //Logging
    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.24.3")
    implementation("org.slf4j:slf4j-api:1.7.36")
}

tasks.jar {
    enabled = false
    dependsOn(tasks["shadowJar"])
}

val jarPath = tasks.jar.get().archiveFile.get().asFile

tasks.shadowJar {
    archiveClassifier.set(null as String?)
    manifest {
        attributes(
            "Main-Class" to "io.github.pclcommunity.pclfx.Main",
            "Add-Opens" to listOf(
                "java.base/java.lang",
                "java.base/java.lang.reflect",
                "java.base/jdk.internal.loader",
                "javafx.base/com.sun.javafx.binding",
                "javafx.base/com.sun.javafx.event",
                "javafx.base/com.sun.javafx.runtime",
                "javafx.graphics/javafx.css",
                "javafx.graphics/com.sun.javafx.stage",
                "javafx.graphics/com.sun.prism",
                "javafx.graphics/com.sun.glass.ui",
                "javafx.controls/com.sun.javafx.scene.control",
                "javafx.controls/com.sun.javafx.scene.control.behavior",
                "javafx.controls/javafx.scene.control.skin",
                "jdk.attach/sun.tools.attach",
            ).joinToString(" "),
            "Enable-Native-Access" to "ALL-UNNAMED"
        )
    }
}