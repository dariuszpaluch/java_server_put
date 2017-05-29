"use strict";

var API = "http://localhost:9999/";

var prepareModel = function (query) {
  var self = ko.observableArray();

  const url = API + query;
  self.url = url;

  self.get = function () {
    $.ajax({
      type: 'GET',
      url: url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      success: function (data) {
        console.log('Pobrano: ' + url);
        data.forEach(function (item) {
          const object = ko.mapping.fromJS(item, { ignore: ["link"] });
          self.push(object);
        });
      },
      error: function (error) {

      }
    });
  };

  self.get();

  return self;
};

function Student(data) {
  this.firstName = ko.observable(data.firstName);
  this.lastName = ko.observable(data.lastName);

}

function studentManagerViewModel() {
  var self = this;
  self.students = prepareModel("students");
  self.courses = prepareModel("courses");
  self.grades = prepareModel("students/1/grades"); //TODO temporary
}


$(document).ready(function () {
  ko.applyBindings(new studentManagerViewModel());
});
