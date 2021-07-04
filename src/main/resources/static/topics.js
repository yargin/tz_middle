const ITEM_ID = 'itemId';

function drawTopic(topic) {
    let template = document.querySelector('#template');
    let templateContent = document.importNode(template.content, true);
    $('#pane').append(templateContent);

    let topicDiv = $('#topic');
    let id = topic.id;
    topicDiv.prop('id', id);

    $(`#${id} #topicName`).val(topic.name);

    $(`#${id} #topicDescription`).val(topic.description);

    let items = topic.items;
    for (let j = 0; j < items.length; j++) {
        drawItem(id, items[j]);
    }

    let addItemId = 'addItem' + id;
    $('#addItem').prop('id', addItemId);
    $(`#${addItemId}`).unbind('click').click(function () {
        addItem(id);
    });


    if (id !== 0) {
        template = document.querySelector('#existingTopicTemplate');
        templateContent = document.importNode(template.content, true);
        topicDiv.append(templateContent);
        $(`#${id} #updateTopic`).click(function () {
            updateTopic(id)
        });
        $(`#${id} #deleteTopic`).click(function () {
            deleteTopic(id)
        });
    } else {
        template = document.querySelector('#newTopicTemplate');
        templateContent = document.importNode(template.content, true);
        topicDiv.append(templateContent);
        $(`#${id} #createTopic`).click(function () {
            createTopic();
        })
    }
}

function drawItem(id, item) {
    let template = document.querySelector('#itemTemplate');
    let templateContent = document.importNode(template.content, true);
    $(`#${id} #items`).append(templateContent);

    let itemId = ITEM_ID + item.id;
    $('#item').prop('id', itemId);
    $(`#${itemId} #itemName`).val(item.name);
    $(`#${itemId} #itemOrder`).val(item.order);

    $(`#${itemId} #deleteItem`).click(function () {
        deleteItem(itemId);
    });
}

function addItem(id) {
    let newItemName = $(`#${id} #newItem #newItemName`);
    let newItemOrder = $(`#${id} #newItem #newItemOrder`);
    var item = {
        "id": 0,
        "name": newItemName.val(),
        "order": newItemOrder.val()
    };
    newItemName.val('');
    newItemOrder.val('');
    drawItem(id, item);
}

function drawTopics(topics) {
    $('#pane').empty();
    for (let i = 0; i < topics.length; i++) {
        drawTopic(topics[i]);
    }
    let newTopic = {
        "id": 0,
        "name": "",
        "description": "",
        "items": []
    };
    drawTopic(newTopic);
}

function getAll() {
    $.ajax({
        url: '/topic',
        success: function (topics) {
            drawTopics(topics);
        }
    });
}

$(function () {
    getAll();
})

function deleteItem(itemId) {
    $(`#${itemId}`).remove();
}

function createTopic() {
    let topic = {
        "id": 0,
        "name": $(`#0 #topicName`).val(),
        "description": $(`#0 #topicDescription`).val(),
        "items": getItems(0)
    };

    console.log(topic);
    $.ajax({
        url: `/topic`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(topic),
        success: function (topics) {
            drawTopics(topics);
        },
        error: function () { alert('wrong data'); }
    })
}

function deleteTopic(id) {
    $.ajax({
        url: `/topic?id=${id}`,
        method: 'DELETE',
        success: function (deleted) {
            if (deleted) {
                $(`#${id}`).remove();
            }
        },
        error: function () { alert('wrong data'); }
    })
}

function getItems(id) {
    let items = [];
    $(`#${id} #items .item`).each(function () {
        let item = {
            "id": $(this).prop('id').substr(ITEM_ID.length),
            "name": $('#itemName', this).val(),
            "order": $('#itemOrder', this).val()
        }
        items.push(item);
    });
    return items;
}

function updateTopic(id) {
    let topic = {
        "id": id,
        "name": $(`#${id} #topicName`).val(),
        "description": $(`#${id} #topicDescription`).val(),
        "items": getItems(id)
    };

    console.log(topic);

    $.ajax({
        url: `/topic?id=${id}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(topic),
        success: function (topics) {
            drawTopics(topics);
        },
        error: function () { alert('wrong data'); }
    })
}