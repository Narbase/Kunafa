import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask

buildscript {

    repositories {
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
    }
}

val deployVersion = "0.4.0"

group = "com.narbase.kunafa"
//archivesBaseName = "kunafa"
version = deployVersion


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
//    id("maven")
    `maven-publish`
    signing
}

repositories {
    mavenLocal()
    mavenCentral()
//    jcenter()
}

kotlin {
    jvm()
    js {
//        moduleName = project.name
        browser {
            testTask {
                useKarma {
                    useChrome()
//                    useChromium()
//                    useFirefox()
                }
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation(kotlin("test-js-runner"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation(kotlin("test-js-runner"))
            }
        }
    }
}

val dokkaHtml = tasks.named<DokkaGeneratePublicationTask>("dokkaGeneratePublicationHtml")

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.flatMap { it.outputDirectory })
}


mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), "kunafa", version.toString())

    pom {
        val projectGitUrl = "https://github.com/Narbase/Kunafa"
        name.set("Kunafa")
        description.set("Easy to use, high level framework in Kotlin for front-end web-development")
        url.set(projectGitUrl)
        inceptionYear.set("2021")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("islam")
                name.set("Islam Abdalla")
                email.set("islam@narbase.com")
                organization.set("Narbase Technologies")
            }
            developer {
                id.set("hind")
                name.set("Hind Abulmaali")
                email.set("hind@narbase.com")
                organization.set("Narbase Technologies")
            }
            developer {
                id.set("ayman")
                name.set("Ayman Hassan")
                email.set("ayman.hassan@narbase.com")
                organization.set("Narbase Technologies")
            }
            developer {
                id.set("shalaga44")
                name.set("Mohamed Moawia")
                email.set("mohamed.moawia@narbase.com")
                organization.set("Narbase Technologies")
            }
        }
        issueManagement {
            system.set("GitHub")
            url.set("$projectGitUrl/issues")
        }
        scm {
            connection.set("scm:git:$projectGitUrl")
            developerConnection.set("scm:git:$projectGitUrl")
            url.set(projectGitUrl)
        }
    }
}


tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}
// To build and publish: ./gradlew clean build publish -Psigning.gnupg.keyName=<KeyId>
// Then manually go to https://oss.sonatype.org, close the staging repo and release