package com.adapterj.example.vertx.spring.web;

import com.adapterj.ext.web.AbstractPage;

/**
 * 
 * @author York/GuangYu DENG
 */
public class ErrorPage extends AbstractPage  {

    private static final long serialVersionUID = -3316948812275299343L;

    protected String title;
    
    protected int waitRedirect = -1;
    protected String redirect;

    protected StringBuffer headers;
    protected StringBuffer body;

    protected boolean logged;

    public ErrorPage(String title) {
        this.title = title;
        this.headers = new StringBuffer();
        this.body = new StringBuffer();
        this.logged = true;
    }

    public ErrorPage(String title, int waitRedirect, String redirect) {
        this(title);
        
        this.waitRedirect = waitRedirect;
        this.redirect = redirect;
    }

    public ErrorPage(String title, boolean logged) {
        this(title);
        
        this.logged = logged;
    }

    public ErrorPage() {
        this("");
    }

    public void write(String html) {
        this.body.append(html);
    }

    public void writeln(String html) {
        this.body.append(html).append('\n');
    }

    /**
     * Attaches CSS style path
     *
     * @param style
     */
    public void attachStyle(String style) {
        headers.append("<link href=\"").append(style).append("\" rel=\"stylesheet\" type=\"text/css\" />").append('\n');
    }

    /**
     * Sets favicon path
     *
     * @param favicon
     */
    public void setFavicon(String favicon) {
        headers.append("<link href=\"").append(favicon).append("\" rel=\"shortcut icon\" />").append('\n');
    }

    /**
     * Returns HTML representation of the document
     *
     * @return
     */
    //@Override
    public String toHTMLString() {
        StringBuffer html = new StringBuffer();
        html.append('\n');

        html.append("<!DOCTYPE html>").append('\n');
        html.append("<html lang=\"en\">").append('\n');
        html.append("<head>").append('\n');
        html.append("<meta charset=\"utf-8\">").append('\n');
        html.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">").append('\n');
        if (waitRedirect >= 0) {
            html.append("<meta http-equiv=\"refresh\" content=\"").append(waitRedirect).append(";url=").append(redirect).append("\">").append('\n');
        }
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">").append('\n');
        html.append("<meta name=\"description\" content=\"\">").append('\n');
        html.append("<meta name=\"author\" content=\"\">").append('\n');
        html.append("<base href=\"/\">").append('\n');

        html.append("<title>").append(title).append("</title>").append('\n');
        html.append(headers).append('\n');

        html.append("<style type=\"text/css\">").append('\n');
        html.append("body { padding-top: 8px; }").append(' ');
        html.append("textarea { width: 100%; }").append(' ');
        html.append(".form-login, .box-logout { max-width: 400px; padding: 8px; margin: 0 auto; }").append(' ');
        html.append(".form-login input { margin-bottom: 10px; }").append(' ');
        html.append(".container { max-width: 800px; }").append(' ');
        html.append(".bg-info { padding: 10px; }").append('\n');
        html.append("</style>").append('\n');

        html.append("</head>").append('\n');
        html.append("<body>").append('\n');

        html.append("<div class=\"container theme-showcase\" role=\"main\">").append('\n');
        html.append(body);
        html.append("</div>").append('\n');

        html.append("<div style=\"text-align:center;clear:both\">").append('\n');
        html.append("</div>").append('\n');
        
        html.append("</body>").append('\n');
        html.append("</html>").append('\n');
        return (html.toString());
    }

    /**
     * Returns HTML representation of the document
     *
     * @return
     */
    @Override
    public String toString() {
        return toHTMLString();
    }
}
