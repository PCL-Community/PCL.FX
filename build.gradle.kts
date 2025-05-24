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

    //Logging
    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.24.3")
    implementation("org.slf4j:slf4j-api:1.7.36")

    //JavaFX
    implementation("com.github.goxr3plus:FX-BorderlessScene:4.4.0")
}

tasks.jar {
    enabled = false
    dependsOn(tasks["shadowJar"])
}

tasks.shadowJar {
    dependencies {
        exclude { it.name.startsWith("org.openjfx") }
    }
    minimize {
        exclude { it.name.startsWith("org.apache") }
    }
    manifest {
        attributes(
            "Main-Class" to "io.github.pclcommunity.pclfx.Main"
        )
    }
}