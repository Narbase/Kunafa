buildscript {

    repositories {
        mavenCentral()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
        classpath(kotlin("gradle-plugin", version = "1.9.21"))
    }
}

val deployVersion = "0.3.1"

group = "com.narbase.kunafa"
//archivesBaseName = "kunafa"
version = deployVersion

val kotlinVersion = "1.9.21"

plugins {
    kotlin("multiplatform")
//    id("maven")
    id("org.jetbrains.dokka")
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
        moduleName = project.name
        browser {
            testTask {
                useKarma {
//                    useChrome()
                    useFirefox()
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
                implementation("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
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
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}


afterEvaluate {
    publishing {
        publications.withType<MavenPublication> {
            artifact(javadocJar.get())
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
            the<SigningExtension>().sign(this)
        }
        repositories {
            maven {
                name = "sonatypeStaging"
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = project.findProperty("SONATYPE_USERNAME") as? String
                    password = project.findProperty("NEXUS_PASSWORD") as? String
                }
            }
        }
    }
}

signing {
    useGpgCmd()
}

// To build and publish: ./gradlew clean build publish -Psigning.gnupg.keyName=<KeyId>
// Then manually go to https://oss.sonatype.org, close the staging repo and release