"use strict";

{
    var myObservableArray = ko.observableArray();    // Initially an empty array
    $.ajax({
        url:
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",

        // Instructions for how to deserialize a `mycustomtype`
        converters: {
            'text mycustomtype': function(result) {
                // Do Stuff
                return newresult;
            }
        },
    });
}
