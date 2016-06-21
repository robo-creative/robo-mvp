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
    private List<SubscriptionToken> mSubscriptionTokens;

    public MessageBusCoordinatorImp(MessageBus bus) {
        mBus = bus;
        mSubscriptionTokens = new ArrayList<>();
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber) {
         return addToken(mBus.subscribe(subscriber));
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority) {
        return addToken(mBus.subscribe(subscriber, priority));
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages) {
        return addToken(mBus.subscribe(subscriber, priority, acceptsChildMessages));
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages) {
        return addToken(mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages));
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, ThreadOption threadOption) {
        return addToken(mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, threadOption));
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, ThreadOption threadOption, boolean keepSubscriberAlive) {
        SubscriptionToken subscriptionToken = mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, threadOption, keepSubscriberAlive);
        if (!keepSubscriberAlive) {
            addToken(subscriptionToken);
        }
        return subscriptionToken;
    }

    @Override
    public <TMessage extends Message> SubscriptionToken subscribe(Subscriber<TMessage> subscriber, int priority, boolean acceptsChildMessages, boolean receiveHistoricMessages, PublishingStrategy<TMessage> publishingStrategy, boolean keepSubscriberAlive) {
        SubscriptionToken subscriptionToken = mBus.subscribe(subscriber, priority, acceptsChildMessages, receiveHistoricMessages, publishingStrategy, keepSubscriberAlive);
        if (!keepSubscriberAlive) {
            addToken(subscriptionToken);
        }
        return subscriptionToken;
    }

    @Override
    public <TMessage extends Message> void unsubscribe(SubscriptionToken subscriptionToken) {
        mBus.unsubscribe(removeToken(subscriptionToken));
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
        for (int i = 0; i < mSubscriptionTokens.size(); i++) {
            unsubscribe(mSubscriptionTokens.get(i));
        }
    }

    private SubscriptionToken addToken(SubscriptionToken subscriptionToken) {
        if (!mSubscriptionTokens.contains(subscriptionToken)) {
            mSubscriptionTokens.add(subscriptionToken);
        }
        return subscriptionToken;
    }

    private SubscriptionToken removeToken(SubscriptionToken subscriptionToken) {
        if (!mSubscriptionTokens.contains(subscriptionToken)) {
            mSubscriptionTokens.remove(subscriptionToken);
        }
        return subscriptionToken;
    }
}
