package com.github.reiseburo.beetle
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
import spock.lang.*


class TopicPartitionSpec extends Specification {
    String topic = 'spock-topic'
    Integer partition = 12

    def "the constructor should set the properties"() {
        given:
        TopicPartition tp = new TopicPartition(topic, partition)

        expect:
        tp.topic == topic
        tp.partition == partition
    }

    def "equals() is true with identical topic/partions"() {
        given:
        TopicPartition left = new TopicPartition(topic, partition)
        TopicPartition right = new TopicPartition(topic, partition)

        expect:
        left == right
    }

    def "equals() is false with two different objects"() {
        given:
        TopicPartition left = new TopicPartition(topic, partition)

        expect:
        left != 'not even'
    }

    def "hashCode() returns an identical hash for identical topic/partitions"() {
        given:
        TopicPartition left = new TopicPartition(topic, partition)
        TopicPartition right = new TopicPartition(topic, partition)

        expect:
        left.hashCode() == right.hashCode()
    }

    def "hashCode() returns a different hash for different topic/partitions"() {
        given:
        TopicPartition left = new TopicPartition(topic, partition)
        TopicPartition right = new TopicPartition('bok', 1)

        expect:
        left.hashCode() != right.hashCode()
    }
}