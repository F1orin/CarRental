/*
 * FormEncodingSetterFilter.java 2014/04/22
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter class for setting the proper encoding for request and responses
 *
 * @author Florin
 */
public class FormEncodingSetterFilter implements Filter {

    private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //get the encoding parameter from web.xml
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        //if no parameter set default encoding
        if (encoding == null) {
            encoding = ENCODING_DEFAULT;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String contentType = req.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
            req.setCharacterEncoding(encoding);
            res.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
