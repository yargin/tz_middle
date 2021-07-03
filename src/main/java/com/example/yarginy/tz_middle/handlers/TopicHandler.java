package com.example.yarginy.tz_middle.handlers;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

@Component
public class TopicHandler implements ResultHandler<Topic> {
    private Topic topic;

    @Override
    public void handleResult(ResultContext<? extends Topic> resultContext) {
        topic = resultContext.getResultObject();
        for (Item item : topic.getItems()) {
            item.setTopic(topic);
        }
    }

    public Topic getTopic() {
        return topic;
    }
}
