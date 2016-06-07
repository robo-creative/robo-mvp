/**
 * Copyright (c) 2016 Robo Creative - https://robo-creative.github.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.robo.mvp;

import java.util.*;

import com.robo.messaging.*;

/**
 * Co-ordinates with a message bus, plus a feature for removing all subscriptions.
 *
 * @author robo-admin
 */
public class MessageBusCoordinatorImp implements MessageBusCoordinator {

    private MessageBus mBus;
    private WeakHashMap<Subscriber<?>, Subscriber<?>> mSubscribers;

    public MessageBusCoordinatorImp(MessageBus bus) {
        mBus = bus;
        mSubscribers = new WeakHashMap<>();
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber) {
        mBus.subscribe(subscriber);
        addSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority) {
        mBus.subscribe(subscriber, priority);
        addSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages) {
        mBus.subscribe(subscriber, priority, acceptsChildMessages);
        addSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages) {
        mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages);
        addSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, ThreadOption threadOption) {
        mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, threadOption);
        addSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, ThreadOption threadOption, boolean keepSubscriberAlive) {
        mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, threadOption, keepSubscriberAlive);
        if (!keepSubscriberAlive) {
            addSubscriber(subscriber);
        }
    }

    @Override
    public <TMessage extends Message> void subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, PublishingStrategy<TMessage> publishingStrategy, boolean keepSubscriberAlive) {
        mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, publishingStrategy, keepSubscriberAlive);
        if (!keepSubscriberAlive) {
            addSubscriber(subscriber);
        }
    }

    @Override
    public <TMessage extends Message> void unsubscribe(Subscriber<TMessage> subscriber) {
        mBus.unsubscribe(subscriber);
        removeSubscriber(subscriber);
    }

    @Override
    public <TMessage extends Message> void publish(TMessage message) {
        mBus.publish(message);
    }

    @Override
    public <TMessage extends Message> void publish(TMessage tMessage, boolean keepInHistory) {
        mBus.publish(tMessage, keepInHistory);
    }

    @Override
    public <TMessage extends Message> void publish(TMessage tMessage, boolean keepInHistory, PublisherCallback callback) {
        mBus.publish(tMessage, keepInHistory, callback);
    }

    @Override
    public <TMessage extends Message> void remove(TMessage tMessage) {
        mBus.remove(tMessage);
    }

    @Override
    public void clearHistory() {
        mBus.clearHistory();
    }

    @Override
    public int getHistoryCount() {
        return mBus.getHistoryCount();
    }

    public void unsubscribeAll() {
        for (Iterator<Map.Entry<Subscriber<?>, Subscriber<?>>> iterator = mSubscribers.entrySet().iterator(); iterator.hasNext(); ) {
            Subscriber<?> subscriber = iterator.next().getKey();
            if (null != subscriber) {
                mBus.unsubscribe(subscriber);
            }
            iterator.remove();
        }
    }

    private void addSubscriber(Subscriber<?> subscriber) {
        if (null != subscriber && !mSubscribers.containsKey(subscriber)) {
            mSubscribers.put(subscriber, subscriber);
        }
    }

    private void removeSubscriber(Subscriber<?> subscriber) {
        if (null != subscriber && mSubscribers.containsKey(subscriber)) {
            mSubscribers.remove(subscriber);
        }
    }
}
