grails.servlet.version = '3.0' // Change depending on target container compliance (2.5 or 3.0)
grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    //test: false,
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    //run: false,
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        mavenRepo 'http://dl.bintray.com/karman/karman' // For CDN Asset Pipeline plugin
    }

    dependencies {
        // Latest Joda Time
        runtime 'joda-time:joda-time:2.3'
        // Latest MySQL Connector
        runtime 'mysql:mysql-connector-java:5.1.30'
        // Latest NewRelic API
        compile 'com.newrelic.agent.java:newrelic-api:3.6.0'
        // Spring Framework ORM
        compile "org.springframework:spring-orm:4.0.4.RELEASE"
    }

    plugins {
        // Core plugins
        build ':tomcat:7.0.53'
        compile ':asset-pipeline:1.8.7'
        runtime ':hibernate4:4.3.5.3' // or ":hibernate:3.6.10.15"

        // Facebook plugin for auth
        runtime ':facebook-sdk:0.6.3'

        // Deployment plugins
        compile ':aws-elastic-beanstalk:0.3-SNAPSHOT'
        compile ':cdn-asset-pipeline:0.3.5'

        // Monitoring
        compile(':newrelic:1.0-2.18.0') {
            excludes 'newrelic-api'
        }
        compile ':raven:0.5.8' // Sentry
        runtime ':segmentio:0.4.3'

    }
}
