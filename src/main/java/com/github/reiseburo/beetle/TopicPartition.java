package com.github.reiseburo.beetle;
/*
*Copyright (c) 2015 Lookout, Inc*

MIT License

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:


The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.


THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.util.Objects;

/**
 */
class TopicPartition {
    private final String topic;
    private final Integer partition;

    public TopicPartition(String topic, Integer partition) {
        this.topic = topic;
        this.partition = partition;
    }

    /**
     * Return true for any two TopicPartition instances that have equal topic
     * and partition properties
     */
    @Override
    public boolean equals(Object compared) {
        /* bail early for object identity */
        if (this == compared) {
            return true;
        }

        if (!(compared instanceof TopicPartition)) {
            return false;
        }

        return (this.topic == ((TopicPartition)compared).topic) &&
            (this.partition == ((TopicPartition)compared).partition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.topic, this.partition);
    }

    @Override
    public String toString() {
        return this.topic + ":" + this.partition;
    }
}
