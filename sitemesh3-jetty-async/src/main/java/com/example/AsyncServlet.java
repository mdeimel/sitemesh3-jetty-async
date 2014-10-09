package com.example;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = getAsyncContext(req, resp);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                resp.setContentType("text/html");
                try {
                    PrintWriter writer = resp.getWriter();
                    writer.println("<html><body><h5>AsyncServlet</h5><p>Content...</p></body></html>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                asyncContext.complete();
            }
        });
    }

    public static AsyncContext getAsyncContext(ServletRequest request, ServletResponse response) {
        AsyncContext asyncContext = null;
        if (request.isAsyncStarted()) {
            asyncContext = request.getAsyncContext();
        }
        else {
            asyncContext = request.startAsync(request, response);
            asyncContext.setTimeout(20000);
        }
        return asyncContext;
    }
}
