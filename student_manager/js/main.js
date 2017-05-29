"use strict";

var API = "http://localhost:9999/";

var prepareModel = function (url, query) {
  var self = ko.observableArray();

  self.url = url;

  self.get = function () {
    $.ajax({
      type: 'GET',
      url: self.url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      success: function (data) {
        data.forEach(function (item) {
          const object = ko.mapping.fromJS(item, { ignore: ["link"] });
          self.push(object);
        });
      },
      error: function (error) {
        console.error(error);
      }
    });
  };

  return self;
};

function studentManagerViewModel() {
  var self = this;
  self.students = prepareModel(API + "students");
  self.students.showGrades = function() {
    var index = this.index();
    self.grades.studentIndex = index;
    self.grades.url = API + "students/" + index + "/grades";
    self.grades.get();
    window.location = "#grades-table";
  };
  self.students.get();

  self.courses = prepareModel(API + "courses");
  self.courses.get();

  self.grades = prepareModel();
}


$(document).ready(function () {
  ko.applyBindings(new studentManagerViewModel());
});
