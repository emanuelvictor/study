package br.org.pti.compras.application.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HTTPContextHolder {
    public static String getServerURL() {
        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        final HttpServletRequest servletRequest = servletRequestAttributes.getRequest();
        final String scheme = servletRequest.getScheme();
        final String serverName = servletRequest.getServerName();
        final int portNumber = servletRequest.getServerPort();
        final String contextPath = servletRequest.getContextPath();

        return scheme + "://" + serverName + (portNumber == 80 ? "" : ":" + portNumber) + (contextPath.isEmpty() ? "" : contextPath);
    }
}
