### What is this?
This is an attempt to use Jetty's Proxy servlet in conjunction with SiteMesh3.

### What's in this repo?
Essentially this is the SiteMesh3 repo. This is because SiteMesh3 does not support asynchronous behavior, and it is currently being added. It seems the best way to include this is to simply make it the parent project (since there is no available SNAPSHOT for these changes).

### How should this work?
The SiteMesh filter applies a yellow background to the `body` tag of the html. So whenever content is properly "decorated" by SiteMesh, you'll see a (blindingly) yellow background.

### How to test?
1. First build SiteMesh. Navigate to root directory, "sitemesh3-jetty-async" and build sitemesh with `gradle jar`
2. Navigate to "sitemesh3-jetty-async" and type `mvn jetty:run`
3. **Proxy** - decorating proxied content can be tested with this url: [http://localhost:8080/jetty](http://localhost:8080/jetty).
..* **Result** - Blank page, `NullPointerException` in `Dispatcher.forward()`.
4. **AsyncServlet** - testing against an asynchronous servlet, so no proxied content: [http://localhost:8080/async](http://localhost:8080/async).
..* **Result** - Blank page, `NullPointerException` in `Dispatcher.forward()`.
5. **Plain HTML** - to test against plain html, navigate to: [http://localhost:8080/test.html](http://localhost:8080/test.html)
..* **Result** - Success, the page is styled, and the `Dispatcher.forward()` does not throw a `NullPointerException` in this case.
6. **Tomcat** - to test the AsyncServlet in Tomcat, generate the war with `mvn package` deploy it to Tomcat, and navigate to [http://localhost:8080/sitemesh3-jetty-async-sitemesh3-jetty-async-0.1-SNAPSHOT/async](http://localhost:8080/sitemesh3-jetty-async-sitemesh3-jetty-async-0.1-SNAPSHOT/async)
..* **Result** - Success, the AsyncServlet's page is styled.
