/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.yegor256.xsline;

import com.jcabi.xml.ClasspathSources;
import com.jcabi.xml.XSL;
import com.jcabi.xml.XSLDocument;
import java.io.IOException;
import java.net.URL;

/**
 * Simple shift from a path in classpath.
 *
 * @since 0.4.0
 * @checkstyle AbbreviationAsWordInNameCheck (3 lines)
 */
public final class StClasspath extends StEnvelope {
    /**
     * Ctor.
     * @param path Path in classpath
     */
    public StClasspath(final String path) {
        super(new StXSL(StClasspath.make(path)));
    }

    /**
     * Make XSL safely.
     * @param path Path in classpath
     * @return XSL
     */
    private static XSL make(final String path) {
        final URL url = StClasspath.class.getResource(path);
        if (url == null) {
            throw new IllegalArgumentException(
                String.format(
                    "Path '%s' not found in classpath", path
                )
            );
        }
        try {
            return new XSLDocument(url).with(new ClasspathSources());
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
