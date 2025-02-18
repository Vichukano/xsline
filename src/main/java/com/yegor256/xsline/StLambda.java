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

import com.jcabi.xml.XML;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * A shift that executes the provided bi-function.
 *
 * @since 0.4.0
 */
public final class StLambda implements Shift {

    /**
     * The UID.
     */
    private final Supplier<String> name;

    /**
     * The function.
     */
    private final BiFunction<Integer, XML, XML> lambda;

    /**
     * Ctor.
     * @param fun The function
     */
    public StLambda(final BiFunction<Integer, XML, XML> fun) {
        this(
            new Supplier<String>() {
                @Override
                public String get() {
                    return String.format("λ-%x", this.hashCode());
                }
            },
            fun
        );
    }

    /**
     * Ctor.
     * @param uid The ID
     * @param fun The function
     */
    public StLambda(final String uid, final BiFunction<Integer, XML, XML> fun) {
        this(() -> uid, fun);
    }

    /**
     * Ctor.
     * @param uid The ID
     * @param fun The function
     */
    public StLambda(final Supplier<String> uid, final BiFunction<Integer, XML, XML> fun) {
        this.name = uid;
        this.lambda = fun;
    }

    @Override
    public String uid() {
        return this.name.get();
    }

    @Override
    public XML apply(final int position, final XML xml) {
        return this.lambda.apply(position, xml);
    }
}
