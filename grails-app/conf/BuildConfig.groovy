grails.project.work.dir = 'target'

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

  inherits "global"
  log "warn"

  repositories {
    grailsCentral()
    mavenLocal()
    mavenCentral()
  }

  dependencies {
    compile 'com.amazonaws:aws-java-sdk:1.9.24' // http://aws.amazon.com/releasenotes/Java?browse=1
  }

  plugins {
    // needed for testing
    build (":tomcat:8.0.20") {
      export = false
    }

    // needed for config management
    compile ':plugin-config:0.2.0'
    compile ':simple-marshaller:0.1.2'

    // needed for userstore
    compile ':aws-instance:0.3.3'

    build(":release:3.1.0", ":rest-client-builder:2.0.3") {
      export = false
    }
  }
}
