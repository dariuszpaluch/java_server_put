"use strict";

var API = "http://localhost:9997/";

var prepareModel = function (url, query) {
  var self = ko.observableArray();

  self.url = url;

  self.get = function () {
    $.ajax({
      type: 'GET',
      url: API + self.url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      success: function (data) {
        // if(self.sub) {
        //   self.sub.dispose();
        // }
        self.removeAll();
        data.forEach(function (item) {
          const object = ko.mapping.fromJS(item, { ignore: ["link"] });

          object.links = [];
          if($.isArray(item.link)) {
            item.link.forEach(function(link) {
              object.links[link.params.rel] = link.href;
            });
          } else {
            object.links[item.link.params.rel] = item.link.href;
          }



          self.push(object);
        });

        console.log("TUTAJ");
        self.sub = self.subscribe(function(changes) {
          console.log(changes);
          changes.forEach(function(change) {
            switch(change.status) {
              case "deleted": {
                console.log("DELETED");
                self.deleteRequest(change.value);
              }
            }
          });
        }, null, "arrayChange");
      },
      error: function (error) {
        console.error(error);
      }
    });
  };

  self.deleteRequest = function(object) {
    $.ajax({
      url: object.links.self,
      method: "DELETE"
    });
  }

  self.delete = function() {
    // $.ajax({
    //   url: object.links['self'],
    //   method: "DELETE"
    // });
    self.remove(this);
  };


  return self;
};

function studentManagerViewModel() {
  var self = this;
  self.students = prepareModel("students");
  self.students.showGrades = function() {
    var index = this.index();
    self.grades.studentIndex = index;
    self.grades.url = "students/" + index + "/grades";
    self.grades.get();
    window.location = "#grades-table";
  };
  self.students.get();

  self.courses = prepareModel("courses");
  self.courses.get();

  self.grades = prepareModel();
}


$(document).ready(function () {
  ko.applyBindings(new studentManagerViewModel());
});
