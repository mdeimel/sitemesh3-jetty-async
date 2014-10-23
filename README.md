### What is this?
This is an attempt to use Jetty's Proxy servlet in conjunction with SiteMesh3.

### What's in this repo?
Essentially this is the SiteMesh3 repo. This is because SiteMesh3 does not support asynchronous behavior, and it is currently being added. It seems the best way to include this is to simply make it the parent project (since there is no available SNAPSHOT for these changes).

### How should this work?
The SiteMesh filter applies a yellow background to the `body` tag of the html. So whenever content is properly "decorated" by SiteMesh, you'll see a (blindingly) yellow background.

### How to build
The following clean setup was used to test the build.
* Ubuntu 14.04 running in VirtualBox on a MacBook Pro running OS X Yosemite
* Gradle 1.10
* Maven 2.2.1
* Oracle JDK 1.8.0_25

Some of these steps might be unnecessary, but this is how I got it to work on a clean system.

1. Navigate to root directory, "sitemesh3-jetty-async" and run `mvn clean package`
2. Then build sitemesh with `gradle jar`
3. Modify the deployment script with `chmod +x deployToMaven.sh`
4. Run `./deployToMaven.sh`
5. Navigate to "sitemesh3-jetty-async" and type `mvn jetty:run`

### How to test?
1. **Proxy** - decorating proxied content can be tested with this url: [http://localhost:8080/jetty](http://localhost:8080/jetty).
  * **Result** - Blank page, `NullPointerException` in `Dispatcher.forward()`.
2. **AsyncServlet** - testing against an asynchronous servlet, so no proxied content: [http://localhost:8080/async](http://localhost:8080/async).
  * **Result** - Blank page, `NullPointerException` in `Dispatcher.forward()`.
3. **Plain HTML** - to test against plain html, navigate to: [http://localhost:8080/test.html](http://localhost:8080/test.html)
  * **Result** - Success, the page is styled, and the `Dispatcher.forward()` does not throw a `NullPointerException` in this case.
4. **Tomcat** - to test the AsyncServlet in Tomcat, generate the war with `mvn package` deploy it to Tomcat, and navigate to [http://localhost:8080/sitemesh3-jetty-async-sitemesh3-jetty-async-0.1-SNAPSHOT/async](http://localhost:8080/sitemesh3-jetty-async-sitemesh3-jetty-async-0.1-SNAPSHOT/async)
  * **Result** - Success, the AsyncServlet's page is styled.
