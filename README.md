[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/com.narbase/kunafa.svg)](https://mvnrepository.com/artifact/com.narbase/kunafa)

<div align="center">
  <img alt="Kunafa logo" src="https://github.com/Narbase/Kunafa/raw/master/logo.png" height="86" />
</div>

<div align="center">
  <h1>Kunafa</h1>
  <p>Easy to use, high level framework in Kotlin for front-end web-development</p>
  <br>
</div>

Create web apps without using HTML, CSS, or JavaScript.

## Documentation

Find Kunafa [Documentation here](https://docs.kunafa.narbase.com/) (Work in progress).

## Philosophy
Web apps framework without using HTML, CSS, or JavaScript.

## Problem

* Web technologies are pain. Html is verbose, CSS is unexpected, and javascript is.. javascript.
* The no. of technologies a developer needs to learn to write web apps is big. That is, HTML, CSS and JavaScript at the least. Then there are javascript frameworks (react, angular, vue), LESS, SCSS, Haml. There are also packaging tools, gulp, webpack.
* Modern javascript frameworks solve the JavaScript problem, not the front end problem. React use JSX (embedded HTML in javascript) while Angular requires CSS and HTML.

## Proposal

An easy to use, high level framework in Kotlin for web development. You do not need to learn the web stack, only the framework to be able to write web apps.

#### Developer experience

Developers only need to use Kotlin for development.

- You write the view (similar to Android xml layouts) in Kotlin DSL. e. g.
```kotlin
            verticalLayout {
                style {
                    width = matchParent
                    height = matchParent
                    backgroundColor = Color(240, 240, 240)
                }
                button {
                    text = "Click me"
                }
            }

  ```

- The view component (similar to Android activity or iOS ViewController) implements certain life cycle functions.
- The framework views contains easy to understand and familiar components and layouts managers. i. e. Button, TextView, TextInput, HorizontalLayout, VerticalLayout and so on.
- The framework makes laying out objects easy, e. g.  match parent, wrap content.
- You can wrap any html, css, and js into a framework component to use it inside the framework.

### Features

* Intuitive DSL for creating views
* Type safe CSS DSL for complete control of views appearance
* Automatic CSS rule sets caching
* Flexible Components to abstract any logic
* Full Routing support (Links, URL params, Redirecting, Navigation control)
* Very easy to wrap any 3rd party library as Kunafa component


### Implementation

- Kotlin transpiles to javascript, and is well designed to support DSLs.
- Code is turned to javascript at compile time, and a basic HTML file loads the generated .js file.
- At runtime, the js file will generate the required HTML, and CSS files containing the whole application.

---
## Getting started

[Hello Kunafa! Getting started guide.](https://github.com/Narbase/Kunafa/wiki/Hello-Kunafa)

To add Kunafa to your project, first you need to add it to your build.gradle file as a dependency. 
```groovy
compile 'com.narbase:kunafa:<latest_version>'
````
If you have Kotlin Js plugin configured, then you can directly use it the code and Webpack will include Kunafa in the 
generated bundle.

Now you are ready to use Kunafa in any Kotlin-js project.

For a complete example, check the [Kunafa Todo repository](https://github.com/Kabbura/kunafa-todo)


---

Your feedback is most welcomed. Let us know how is your experience with Kunafa. 
